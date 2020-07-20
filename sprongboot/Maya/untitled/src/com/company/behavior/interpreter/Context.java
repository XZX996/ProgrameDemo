package com.company.behavior.interpreter;

public class Context {
    private String[] citys={"韶关","广州"};
    private String[] persons={"老人","妇女","儿童"};
    private MyExpression cityPerson;
    public Context()
    {
        MyExpression city=new TerminalExpression(citys);
        MyExpression person=new TerminalExpression(persons);
        cityPerson=new AndExpression(city,person);
    }
    public void freeRide(String info)
    {
        boolean ok=cityPerson.interpret(info);
        if(ok) System.out.println("您是"+info+"，您本次乘车免费！");
        else System.out.println(info+"，您不是免费人员，本次乘车扣费2元！");
    }
}
