package com.xzx.activityListen;

import org.activiti.engine.delegate.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class AssigneeTask implements TaskListener {

    @Override
    public void notify(DelegateTask delegateTask) {
        Map<String,Object> map = delegateTask.getVariables();
        delegateTask.addCandidateGroup("HAHA");
        delegateTask.addCandidateUser("HAHA1");
        delegateTask.addCandidateUser("HAHA2");
        delegateTask.addCandidateUser("HAHA3");
        //delegateTask.setAssignee(map.get("userID").toString()+",erzi");
        System.out.println(map);
    }
}