package com.company.CreateType.Builder;

public abstract class OneBuilder {
    protected OneProduct product=new OneProduct();
    public abstract void buildPartA();
    public abstract void buildPartB();
    public abstract void buildPartC();
    //返回产品对象
    public final OneProduct getResult()
    {
        System.out.println("抽象类生成");
        return product;
    }

}
