package com.company.behavior.interpreter;

import com.sun.org.apache.xpath.internal.operations.Bool;

/**
 * 抽象表达式
 */
public interface MyExpression {
    public boolean interpret(String info);
}
