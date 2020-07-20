package com.company.behavior.visitor;

public class MainTest {
    public static void main(String[] args)
    {
        ObjectStruct os=new ObjectStruct();
        os.add(new ConcreteObjA());
        os.add(new ConcreteObjB());
        AbsVisitor visitor=new ConcreteVisitorA();
        os.accept(visitor);
        System.out.println("------------------------");
        visitor=new ConcreteVisitorB();
        os.accept(visitor);
    }
}
