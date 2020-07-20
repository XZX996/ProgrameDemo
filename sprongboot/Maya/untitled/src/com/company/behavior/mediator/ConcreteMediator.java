package com.company.behavior.mediator;

import java.util.ArrayList;
import java.util.List;

public class ConcreteMediator extends AbsMediator {

    private List<Colleague> colleagues=new ArrayList<Colleague>();
    //注册
    @Override
    public void register(Colleague colleague)
    {
        if(!colleagues.contains(colleague))
        {
            colleagues.add(colleague);
            colleague.setMedium(this);
        }
    }
    //转发
    @Override
    public void relay(Colleague cl)
    {
        for(Colleague ob:colleagues)
        {
            if(!ob.equals(cl))
            {
                ((Colleague)ob).receive();
            }
        }
    }
}
