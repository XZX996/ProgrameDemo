package com.company.behavior.state;

public class ConcreteStateA extends AbsState {
    @Override
    public void Handler(Context context) {
        System.out.println("当前状态是 A.");
        context.setState(new ConCreteStateB());
    }
}
