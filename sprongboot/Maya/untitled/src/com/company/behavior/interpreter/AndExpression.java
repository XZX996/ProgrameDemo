package com.company.behavior.interpreter;

import java.util.HashSet;
import java.util.Set;

/***
 * 终结者操作符
 */
public class AndExpression implements MyExpression {
    private MyExpression city=null;
    private MyExpression person=null;
    public AndExpression(MyExpression city,MyExpression person)
    {
        this.city=city;
        this.person=person;
    }
    @Override
    public boolean interpret(String info)
    {
        String s[]=info.split("的");
        return city.interpret(s[0])&&person.interpret(s[1]);
    }
}
