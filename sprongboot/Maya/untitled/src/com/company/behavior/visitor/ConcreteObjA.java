package com.company.behavior.visitor;

public class ConcreteObjA implements MyObject {
    @Override
    public void accept(AbsVisitor visitor) {
        visitor.visit(this);
    }
    public String operationA()
    {
        return "具体元素A的操作。";
    }
}
