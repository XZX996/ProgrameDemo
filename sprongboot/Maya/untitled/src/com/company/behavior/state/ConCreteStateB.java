package com.company.behavior.state;

public class ConCreteStateB extends  AbsState {
    @Override
    public void Handler(Context context) {
        System.out.println("当前状态是 B.");
        context.setState(new ConcreteStateA());
    }
}
