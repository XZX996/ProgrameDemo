package com.company.behavior.interpreter;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.HashSet;
import java.util.Set;

/***
 * 终结者操作符
 */
public class TerminalExpression implements MyExpression {
    private Set<String> set= new HashSet<String>();
    public TerminalExpression(String[] data)
    {
        for(int i=0;i<data.length;i++)set.add(data[i]);
    }
    @Override
    public boolean interpret(String info)
    {
        if(set.contains(info))
        {
            return true;
        }
        return false;
    }
}
