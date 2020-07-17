package com.company.structural.compose.transparentModel;

public class MainTest {
    public static void main(String[] args)
    {
        //创建树枝
        Component c0=new Composite();
        //创建树枝1
        Component c1=new Composite();
        Component leaf1=new leaf("1");
        Component leaf2=new leaf("2");
        Component leaf3=new leaf("3");
        c0.add(leaf1);
        c0.add(c1);
        c1.add(leaf2);
        c1.add(leaf3);
        c0.operation();
    }
}
