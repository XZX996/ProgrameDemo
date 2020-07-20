package com.company.behavior.command;

public class Invoker {
    private AbsCommand command;
    public Invoker(AbsCommand command)
    {
        this.command=command;
    }
    public void setCommand(AbsCommand command)
    {
        this.command=command;
    }
    public void call()
    {
        System.out.println("调用者执行命令command...");
        command.execute();
    }
}
