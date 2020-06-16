package com.example.demo.v1.netty;

import lombok.SneakyThrows;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

public class ServerSocketChannelClientDemo implements Runnable{

    private Selector selector;
    private SocketChannel socketChannel;
    private volatile  boolean stop = false;


    @SneakyThrows
    public ServerSocketChannelClientDemo(){
        selector = Selector.open();
        socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
    }

    @SneakyThrows
    private void connect(){
        if(socketChannel.connect(new InetSocketAddress("localhost",8080))){
            socketChannel.register(selector, SelectionKey.OP_READ);
            //
            doWrite(socketChannel);
        }else {
            socketChannel.register(selector,SelectionKey.OP_CONNECT);
        }
    }

    @SneakyThrows
    private void doWrite(SocketChannel sc){
        byte[] req = "hello nio".getBytes();
        ByteBuffer writeBuffer = ByteBuffer.allocate(req.length);
        writeBuffer.put(req);
        writeBuffer.flip();
        sc.write(writeBuffer);
        if(!writeBuffer.hasRemaining()){
            //已经全部发完
        }
    }


    @Override
    public void run() {
        connect();
        while (!stop){
            try {
                selector.select(1000);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                SelectionKey key;
                while (iterator.hasNext()){
                    key = iterator.next();
                    iterator.remove();
                    try {
                        handleInput(key);
                    }catch (Exception e){
                        if(key != null){
                            key.channel();
                            if(key.channel() != null){
                                key.channel().close();
                            }
                        }
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        if(selector != null){
            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @SneakyThrows
    private void handleInput(SelectionKey key) {
        if(key.isValid()){
            SocketChannel channel = (SocketChannel) key.channel();
            if(key.isConnectable()){
                if(channel.finishConnect()) {
                    channel.register(selector, SelectionKey.OP_READ);
                    doWrite(channel);
                }
            }

            if(key.isReadable()){
                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                int length = channel.read(readBuffer);
                if(length > 0){
                    readBuffer.flip();
                    byte[] bytes = new byte[readBuffer.remaining()];
                    readBuffer.get(bytes);
                    String body = new String(bytes, StandardCharsets.UTF_8);
                    System.out.println(body);
                    this.stop = true;
                }else if(length < 0){
                    key.cancel();
                    channel.close();
                }
            }
        }
    }
}
