package com.company.behavior.state;

//环境类
class Context
{
    private AbsState state;
    //定义环境类的初始状态
    public Context()
    {
        this.state=new ConcreteStateA();
    }
    //设置新状态
    public void setState(AbsState state)
    {
        this.state=state;
    }
    //读取状态
    public AbsState getState()
    {
        return(state);
    }
    //对请求做处理
    public void Handle()
    {
        state.Handler(this);
    }
}