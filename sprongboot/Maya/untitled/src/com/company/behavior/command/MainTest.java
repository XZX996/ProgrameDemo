package com.company.behavior.command;

public class MainTest {
    public static void main(String[] args)
    {
        AbsCommand cmd=new ConcreteCommand();
        //cmd.execute();
        Invoker ir=new Invoker(cmd);
        System.out.println("客户访问调用者的call()方法...");
        ir.call();
    }
}
