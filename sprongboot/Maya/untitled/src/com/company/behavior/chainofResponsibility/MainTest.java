package com.company.behavior.chainofResponsibility;

public class MainTest {
    public static void main(String[] args)
    {
        //组装责任链
        AbsHandler handler1=new ConcreteHandler1();
        AbsHandler handler2=new ConcreteHandler2();
        handler1.setNext(handler2);
        //提交请求
        handler1.handleRequest("one");
        //
        handler1.handleRequest("two");
        handler1.handleRequest("third");
    }
}
