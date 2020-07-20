package com.company.behavior.visitor;

public class ConcreteObjB implements MyObject {
    @Override
    public void accept(AbsVisitor visitor) {
        visitor.visit(this);
    }
    public String operationB()
    {
        return "具体元素B的操作。";
    }
}
