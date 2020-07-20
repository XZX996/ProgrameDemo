package com.company.behavior.visitor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//对象结构角色
public class ObjectStruct {
    private List<MyObject> list=new ArrayList<MyObject>();
    public void accept(AbsVisitor visitor)
    {
        Iterator<MyObject> i=list.iterator();
        while(i.hasNext())
        {
            ((MyObject) i.next()).accept(visitor);
        }
    }
    public void add(MyObject element)
    {
        list.add(element);
    }
    public void remove(MyObject element)
    {
        list.remove(element);
    }
}
