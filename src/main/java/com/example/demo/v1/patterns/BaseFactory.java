package com.example.demo.v1.patterns;

/**
 * 工厂模式-根据className获取具体的工厂实体
 */
public class BaseFactory {

    /**
     *
     * @param className 具体的工厂实例
     * @param <T>
     * @return
     */
    public static<T> T getFactory(String className){
        T object = null;
        try {
            object = (T) Class.forName(className).newInstance();
        }catch (Exception e){
            e.printStackTrace();
        }
        return object;
    }

}
