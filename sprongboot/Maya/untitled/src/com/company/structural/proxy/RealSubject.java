package com.company.structural.proxy;

public class RealSubject implements subject {

    @Override
    public void request() {
        System.out.println("访问真实主题方法...");
    }
}
