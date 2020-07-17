package com.company.CreateType.Factory;

/**
 * 具体实现工厂
 */
public class ConcreateFactory1 implements MyAbstractFactory {


    @Override
    public MyProduct newProduct() {
        System.out.println("具体工厂1=======》开始加工");
        return new ConcreteProduct1();
    }
}
