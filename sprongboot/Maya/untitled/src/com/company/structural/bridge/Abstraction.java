package com.company.structural.bridge;

public abstract class Abstraction {
    protected Implementor imple;
    //构造注入或者set注入都行
    protected Abstraction(Implementor imple)
    {
        this.imple=imple;
    }
    public abstract void Operation();
}
