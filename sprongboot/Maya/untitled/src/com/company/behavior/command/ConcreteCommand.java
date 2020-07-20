package com.company.behavior.command;

public class ConcreteCommand implements AbsCommand {
    private Receiver receiver;
    ConcreteCommand()
    {
        receiver=new Receiver();
    }
    @Override
    public void execute() {
        receiver.action();
    }
}
