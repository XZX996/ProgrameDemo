package com.company.behavior.iterator;

/**
 * 抽象迭代器
 */
public interface MyIterator {
    Object first();
    Object next();
    boolean hasNext();
}
