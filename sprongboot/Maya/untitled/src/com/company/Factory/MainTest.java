package com.company.Factory;

import java.io.IOException;

public class MainTest {

    public static void main(String[] args){

        try
        {
            MyProduct a;
            MyAbstractFactory af;
            //这里可以使用配置文件的方式获取那个工厂
            //af=(MyAbstractFactory) ReadXML1.getObject();
            //为了测试方便 我们指定一个工厂
            af = new ConcreateFactory1();
            a=af.newProduct();
            a.show();
            af = new ConcreateFactory2();
            a=af.newProduct();
            a.show();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }

    }

}

