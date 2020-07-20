package com.company.behavior.strategy;

public class MainTest {
    public static void main(String[] args)
    {
        Context c=new Context();
        AbsStrategy s=new ConcreteStrategyA();
        c.setStrategy(s);
        c.strategyMethod();
        System.out.println("-----------------");
        s=new ConcreteStrategyB();
        c.setStrategy(s);
        c.strategyMethod();
    }
}
