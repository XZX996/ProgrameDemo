package com.company.structural.proxy;

public class Maintest {
    public static void main(String[] args)
    {
        RealSubject real=new RealSubject();
        proxyclass proxy=new proxyclass(real);
        proxy.request();
    }
}
