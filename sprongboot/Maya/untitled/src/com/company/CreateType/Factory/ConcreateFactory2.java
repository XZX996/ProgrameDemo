package com.company.CreateType.Factory;

public class ConcreateFactory2 implements MyAbstractFactory {
    @Override
    public MyProduct newProduct() {
        System.out.println("具体工厂2=======》开始加工");
        return new ConcreteProduct2();
    }
}
