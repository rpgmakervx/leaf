package com.easyarch.leaf.demo;

import org.easyarch.vsf.annotation.Implement;

/**
 * Created by xingtianyu(code4j) on 2017-8-21.
 */
@Implement(lookup = "Service")
public class Service implements IService{

    @Override
    public void helloWorld(String msg) {
        System.out.println("hello,"+msg);
    }

    @Override
    public int calculate(int a, int b) {
        return a+b;
    }
}
