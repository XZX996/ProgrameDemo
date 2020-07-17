package com.company.structural.Flyweight;

/**
 * 非享元（Unsharable Flyweight)
 */
public class UnsharedConcreteFlyweight {
    private String info;
    UnsharedConcreteFlyweight(String info)
    {
        this.info=info;
    }
    public String getInfo()
    {
        return info;
    }
    public void setInfo(String info)
    {
        this.info=info;
    }
}
