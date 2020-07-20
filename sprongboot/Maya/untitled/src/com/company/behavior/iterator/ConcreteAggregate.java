package com.company.behavior.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * 具体聚合者
 */
public class ConcreteAggregate implements MyAggregate {
    private List<Object> list=new ArrayList<Object>();
    @Override
    public void add(Object obj) {
        list.add(obj);
    }

    @Override
    public void remove(Object obj) {
        list.remove(obj);
    }

    //申明一个迭代器
    @Override
    public MyIterator getIterator() {
        return new ConcreteIterator(list);
    }
}
