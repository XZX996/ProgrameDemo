package com.company.behavior.iterator;

//抽象聚合
public  interface MyAggregate {
    public void add(Object obj);
    public void remove(Object obj);
    public MyIterator getIterator();
}
