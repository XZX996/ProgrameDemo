package com.company.behavior.chainofResponsibility;

/**
 * 抽象处理者
 */
public abstract class AbsHandler {
    private AbsHandler next;
    public void setNext(AbsHandler next)
    {
        this.next=next;
    }
    public AbsHandler getNext()
    {
        return next;
    }
    //处理请求的方法
    public abstract void handleRequest(String request);
}
