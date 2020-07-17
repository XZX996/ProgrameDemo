package com.company.structural.decorator;

public class MainTest {
    public static void main(String[] args)
    {
        person employee = new ItPerson();
        employee = new ManageA(employee);//赋予程序猿项目经理A职责
        employee.doCoding();

    }
}
