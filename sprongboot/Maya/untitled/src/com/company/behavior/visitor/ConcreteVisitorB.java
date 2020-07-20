package com.company.behavior.visitor;

public class ConcreteVisitorB implements AbsVisitor {
    @Override
    public void visit(ConcreteObjA element) {
        System.out.println("具体访问者B访问-->"+element.operationA());
    }

    @Override
    public void visit(ConcreteObjB element) {
        System.out.println("具体访问者B访问-->"+element.operationB());
    }
}
