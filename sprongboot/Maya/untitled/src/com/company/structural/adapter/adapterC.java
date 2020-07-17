package com.company.structural.adapter;

/**
 * 适配者接口，它是被访问和适配的现存组件库中的组件接口。
 */
public class adapterC {
    public void specificRequest(){
        System.out.println("适配者中的业务代码被调用：在这里处理目标类的数据");
    }
}
