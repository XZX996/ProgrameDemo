package com.company.structural.adapter;

public class MainTest {
    public static void main(String[] args){

        System.out.println("类适配测试");
        targetclass tar=new ClassAdpterC();
        tar.request();

        System.out.println("对象适配器模式测试：");
        adapterC adaptee = new adapterC();
        tar = new objAdapter(adaptee);
        tar.request();
    }

}
