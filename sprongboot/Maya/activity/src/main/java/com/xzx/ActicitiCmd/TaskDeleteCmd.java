package com.xzx.ActicitiCmd;

import org.activiti.engine.impl.cmd.NeedsActiveExecutionCmd;
import org.activiti.engine.impl.cmd.NeedsActiveTaskCmd;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntityManagerImpl;

/**
 * 删除任务命令
 */
public class TaskDeleteCmd extends NeedsActiveTaskCmd<String> {

    public TaskDeleteCmd(String taskid) {
        super(taskid);
    }

    @Override
    protected String execute(CommandContext commandContext, TaskEntity currentTask) {
        TaskEntityManagerImpl taskEntityManager=(TaskEntityManagerImpl)commandContext.getTaskEntityManager();
        ExecutionEntity executionEntity=currentTask.getExecution();
        taskEntityManager.deleteTask(currentTask,"reject",false,false);
        return executionEntity.getId();
    }
}
