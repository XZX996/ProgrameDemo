package com.company.behavior.visitor;

/**
 * 抽象元素类
 */
public interface MyObject {
    void accept(AbsVisitor visitor);
}
