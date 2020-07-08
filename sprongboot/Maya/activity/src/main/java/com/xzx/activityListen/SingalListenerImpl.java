package com.xzx.activityListen;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.delegate.TaskListener;

public class SingalListenerImpl implements TaskListener, ExecutionListener {
    @Override
    public void notify(DelegateTask arg0) {
        System.out.println("Singal Event Task Is Running");
    }

    @Override

    public void notify(DelegateExecution arg0) {

        // TODO Auto-generated method stub
        System.out.println("Singal Event Execution Is Running");
    }
}
