package com.company.behavior.iterator;

import java.util.List;

/**
 * 具体迭代者,将遍历拆分出来
 */
public class ConcreteIterator implements MyIterator {
    private List<Object> list=null;
    private int index=-1;
    public ConcreteIterator(List<Object> list)
    {
        this.list=list;
    }
    @Override
    public boolean hasNext()
    {
        if(index<list.size()-1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    @Override
    public Object first()
    {
        index=0;
        Object obj=list.get(index);;
        return obj;
    }
    @Override
    public Object next()
    {
        Object obj=null;
        if(this.hasNext())
        {
            obj=list.get(++index);
        }
        return obj;
    }
}
