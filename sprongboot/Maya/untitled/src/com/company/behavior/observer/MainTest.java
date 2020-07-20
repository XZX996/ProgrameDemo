package com.company.behavior.observer;

public class MainTest {
    public static void main(String[] args)
    {
        MySubject subject=new ConcreteSubject();
        Observer obs1=new ConcreteObserver1();
        Observer obs2=new ConcreteObserver2();
        subject.add(obs1);
        subject.add(obs2);
        subject.notifyObserver();
    }
}
