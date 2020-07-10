package com.company.Builder;

public class Director {
    private OneBuilder builder;
    public Director(OneBuilder builder)
    {
        this.builder=builder;
    }
    //产品构建与组装方法
    public OneProduct construct()
    {
        builder.buildPartA();
        builder.buildPartB();
        builder.buildPartC();
        return builder.getResult();
    }
}
