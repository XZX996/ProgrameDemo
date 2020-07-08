package com.xzx.activityListen;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class ErrorRuningTask implements JavaDelegate {

        //重写委托的提交方法

        @Override

        public void execute(DelegateExecution execution) {

            //receiveTaskprocess

            System.out.println("NormalErrorTestRunningTask is running!");

        }

}
