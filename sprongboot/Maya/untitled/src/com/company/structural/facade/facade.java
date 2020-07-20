package com.company.structural.facade;

public class facade {

    private childnode1 ch1=new childnode1();
    private childnode2 ch2=new childnode2();
    private childnode3 ch3=new childnode3();
    public void method()
    {
        ch1.method1();
        ch2.method2();
        ch3.method3();
    }

}
