package com.company.behavior.visitor;

public interface AbsVisitor {
    void visit(ConcreteObjA element);
    void visit(ConcreteObjB element);
}
