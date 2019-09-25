package com.example.demo.v1.common;

public class BaseSingleton {

    private static BaseSingleton BASE_SINGLETON = new BaseSingleton();

    private BaseSingleton(){}

    public BaseSingleton getInstance(){
        return BASE_SINGLETON;
    }
}
