package com.company.behavior.mediator;

/**
 * 抽象中介者
 */
public abstract class AbsMediator {
    public abstract void register(Colleague colleague);
    public abstract void relay(Colleague cl); //转发
}
