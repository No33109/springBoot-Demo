package com.example.demo.v1.netty;

import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

public class ServerSocketChannelDemo implements Runnable{

    private Selector selector;
    private volatile  boolean flag = false;

    public ServerSocketChannelDemo() {
        try {
            ServerSocketChannel socketChannel = ServerSocketChannel.open();
            socketChannel.socket().bind(new InetSocketAddress(InetAddress.getByName("localhost"),8080));
            //设置非阻塞模式
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_ACCEPT);
            //创建selector
            selector = Selector.open();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public void stop(){
        this.flag = true;
    }

    @SneakyThrows
    @Override
    public void run() {
        while (!flag){
            selector.select(1000);
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            SelectionKey next;
            while (iterator.hasNext()){
               next = iterator.next();
               iterator.remove();
               try {
                   this.handleInput(next);
               }catch (Exception e){
                  if(next != null){
                      next.cancel();
                      if(next.channel() != null){
                          next.channel().close();
                      }
                  }
               }
            }
        }

        if(selector != null){
            selector.close();
        }
    }

    @SneakyThrows
    public void handleInput(SelectionKey key){
        if(key.isValid()){
            //处理新接入的请求信息
            if(key.isAcceptable()){
                ServerSocketChannel ssc = (ServerSocketChannel)key.channel();
                SocketChannel channel = ssc.accept();
                channel.configureBlocking(false);
                channel.socket().setReuseAddress(true);
                channel.register(selector,SelectionKey.OP_READ);

            }
            if(key.isReadable()){
                SocketChannel channel = (SocketChannel)key.channel();
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                int read = channel.read(byteBuffer);
                if(read > 0){
                    byteBuffer.flip();
                    byte[] bytes = new byte[byteBuffer.remaining()];
                    byteBuffer.get(bytes);
                    String body = new String(bytes, StandardCharsets.UTF_8);
                    this.doWrite(channel,body);
                }else if(read < 0){
                    key.channel();
                    channel.close();
                }
            }
        }
    }

    @SneakyThrows
    private void doWrite(SocketChannel channel,String response){
        if(!StringUtils.isBlank(response)){
            byte[] bytes = response.getBytes();
            ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
            writeBuffer.put(bytes);
            writeBuffer.flip();
            channel.write(writeBuffer);
        }
    }
}
