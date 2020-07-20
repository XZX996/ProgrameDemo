package com.company.behavior.mediator;

/**
 * 抽象同事类
 *
 */
public abstract class Colleague {
    protected AbsMediator mediator;
    public void setMedium(AbsMediator mediator)
    {
        this.mediator=mediator;
    }
    public abstract void receive();
    public abstract void send();
}
