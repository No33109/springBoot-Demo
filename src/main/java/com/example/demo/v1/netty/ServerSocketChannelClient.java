package com.example.demo.v1.netty;

public class ServerSocketChannelClient {

    public static void main(String[] args) {
        ServerSocketChannelDemo demo = new ServerSocketChannelDemo();
        new Thread(demo).start();

        //轮询停止
        demo.stop();
    }
}
