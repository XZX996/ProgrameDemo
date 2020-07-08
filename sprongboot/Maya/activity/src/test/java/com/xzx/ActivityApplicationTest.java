package com.xzx;

import com.xzx.ActicitiCmd.ExecutionVariableDeleteCmd;
import com.xzx.ActicitiCmd.FlowToFirstCmd;
import com.xzx.ActicitiCmd.TaskDeleteCmd;
import org.activiti.engine.*;
import org.activiti.engine.impl.util.Activiti5Util;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.plaf.synth.Region;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivityApplicationTest {

	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private TaskService taskService;
    @Autowired
    private ManagementService managementService;


	@Test
	public void contextLoads() {
		//String[] args=new String[]{};
		//要从xml中读取配置
		//DbSchemaCreate.main(args);
	}

	@Test
	public void deploy() {
		//得到流程引擎的方式三，利用底层封装，来加载配置文件，只需要调用方法即可
		ProcessEngine pec = ProcessEngines.getDefaultProcessEngine();

		//部署的服务对象
		RepositoryService repositoryService = pec.getRepositoryService();

        //Activiti5Util()
		//部署请假任务
		Deployment deploy = repositoryService.createDeployment()
				.addClasspathResource("processes/qingjia2.bpmn")
				//.addClasspathResource("process/qingjia.png")
				.key("myProcess_3")
				.name("请假3")  //添加部署的名称
				.deploy();

		System.out.println("请假部署ID：" + deploy.getId());
		System.out.println("部署名称"+deploy.getName());
	}

	/**
	 *启动流程实例
	 */
	@Test
	public void startProcessInstance() {
		//ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
		String processDefinitionKey = "myProcess_3";Map<String, Object> variables = new HashMap<>();
        variables.put("outcome","0");

		ProcessInstance processInstance =runtimeService.startProcessInstanceByKey(processDefinitionKey,"12",variables);
		//ProcessInstance processInstance = processEngine.getRuntimeService()//与正在执行的流程实例和执行对象相关的Service
				//.startProcessInstanceByKey(processDefinitionKey);//根据Key值来查询流程,也可以根据ID
		System.out.println("流程实例ID:"+processInstance.getId());  //2501
		//对应数据库act_ru_execution
		System.out.println("流程定义ID:"+processInstance.getProcessDefinitionId()); //helloword:1:4
		//对应数据库act_re_deployment
	}

	/**
	 * 查询当前个人的任务
	 */
	@Test
	public void fingByPerson() {
		String assignee = "员工";
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

		List<Task> list = processEngine.getTaskService()//与正在执行的任务管理相关的Service
				.createTaskQuery()//创建任务查询对象
				.taskAssignee(assignee)//指定个人任务查询，指定代理人
				.list();//以list形式记录对象
		if(list != null && list.size()>0) {
			for(Task task:list) {
				System.out.println("任务ID:"+task.getId());//2501
				System.out.println("任务名称："+task.getName());//提交申请
				System.out.println("任务的创建时间："+task.getCreateTime());//Wed Jun 06 18:12:15 CST 2018
				System.out.println("任务的代理人："+task.getAssignee());//张三
				System.out.println("流程实例ID："+task.getProcessInstanceId());//2501
				System.out.println("执行对象ID："+task.getExecutionId());//2501
				System.out.println("流程定义ID："+task.getProcessDefinitionId());//helloword:1:4
			}
		}
	}

	/**
	 * 完成个人任务
	 */
	@Test
	public void complete() {
		String taskId = "45003";
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
		processEngine.getTaskService().setVariable(taskId, "outcome", "PASS");
		processEngine.getTaskService()
		.complete(taskId);
		System.out.println("完成任务ID："+taskId);
	}



	//region hahahh
	/**
	 * 获取流程变量
	 */
	@Test
	public void GetRunVariable() {
		Map<String, Object> params = new HashMap<>();
		params.put("startVariable", "this is startVariable");
		//ProcessInstance instance = runtimeService.startProcessInstanceByKey("myProcess_2", params);
		String taskId = "32501";
		System.out.println("流程ID："+runtimeService.getVariableLocal(taskId,"startVariable"));
		/*String taskId = "15005";
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
		processEngine.getTaskService().setVariable(taskId,"variable","haha");
		processEngine.getTaskService().setVariableLocal(taskId, "localVariable", "this is Local Variable");
		processEngine.getTaskService().getVariable(taskId,"variable");
		System.out.println("开始====>");
		System.out.println("getVariable(taskId,\"variable\")===>"+processEngine.getTaskService().getVariable(taskId,"variable"));
		System.out.println("getVariable(taskId,\"localVariable\")===>"+processEngine.getTaskService().getVariable(taskId,"localVariable"));
		System.out.println("getVariableLocal(taskId,\"variable\")===>"+processEngine.getTaskService().getVariableLocal(taskId,"variable"));
		System.out.println("getVariableLocal(taskId,\"localVariable\")===>"+processEngine.getTaskService().getVariableLocal(taskId,"localVariable"));
	    */
	}
	//endregion

    /**
     * 删除流程
     */
    @Test
    public void deletePro(){
        Map<String,String> a=new HashMap<String, String>();
        a.put("50001","审批");
        a.put("40001","老板");
        a.put("15001","请假");
        a.put("32501","请假");

        a.forEach((key, Value)->{

        });
        for(Map.Entry<String,String> entry: a.entrySet()){
            ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
            processEngine.getRuntimeService().deleteProcessInstance(entry.getKey(),entry.getValue());
        }

    }
    /**
     * 删除部署信息 
     * */
    @Test
    public void deleteDeployment(){
       String deploymentId="1";
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
       // 第二个参数代表级联操作  
        processEngine.getRepositoryService().deleteDeployment(deploymentId,true);
        // 删除所有相关的activiti信息  
    }



	/**
	 * 获取TaskService流程变量
	 */
	@Test
	public void GetVariable() {
		String taskId = "15005";
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
		processEngine.getTaskService().setVariable(taskId,"variable","haha");
		processEngine.getTaskService().setVariableLocal(taskId, "localVariable", "this is Local Variable");
		processEngine.getTaskService().getVariable(taskId,"variable");
		System.out.println("开始====>");
		System.out.println("getVariable(taskId,\"variable\")===>"+processEngine.getTaskService().getVariable(taskId,"variable"));
		System.out.println("getVariable(taskId,\"localVariable\")===>"+processEngine.getTaskService().getVariable(taskId,"localVariable"));
		System.out.println("getVariableLocal(taskId,\"variable\")===>"+processEngine.getTaskService().getVariableLocal(taskId,"variable"));
		System.out.println("getVariableLocal(taskId,\"localVariable\")===>"+processEngine.getTaskService().getVariableLocal(taskId,"localVariable"));
	}

    /**
     * 拒绝
     */
    @Test
    public void reject() {
        String taskId = "67505";
        //删除所有当前task，保留一个，并且将该task的审批人设为发起人
        //设置reject标志
        Task t = taskService.createTaskQuery()
                .taskId(taskId)
                .singleResult();
        String instanceId = t.getProcessInstanceId();
        List<Task> tasks = taskService.createTaskQuery()
                .processInstanceId(instanceId)
                .list();
        Task luckyTask = tasks.get(0);
        managementService.executeCommand(new ExecutionVariableDeleteCmd(t.getExecutionId()));
        //taskService.removeVariable(taskId,"outcome");
        for (int i = 1; i < tasks.size(); ++i) {
            //taskService.deleteTask(tasks.get(i).getId());
           // taskService.removeVariable(tasks.get(i).getExecutionId(),"outcome");
            managementService.executeCommand(new TaskDeleteCmd(tasks.get(i).getId()));
            managementService.executeCommand(new ExecutionVariableDeleteCmd(tasks.get(i).getExecutionId()));
        }
        //(String) taskService.getVariable(luckyTask.getId(), "submitter")
        //将发起人设置为当前审批人
        taskService.setAssignee(luckyTask.getId(), "请假");
        //设置变量标识当前状态是已拒绝状态
        taskService.setVariable(luckyTask.getId(), "outcome", "reject");
        System.out.println(t.toString()+"::"+instanceId);
        //return this.taskResponse(t, instanceId);
    }

    /**
     * 拒绝后审批
     */
    @Test
    public void Audit(){
        String taskId = "72503";
        String status = (String) taskService.getVariable(taskId, "outcome");
        if ("reject".equals(status)) {
            //发起人重新发起
            this.rollbackFirstask(taskId, "请假");
        } else {
            taskService.setVariable(taskId, "outcome","PASS");
            //正常审批
            taskService.complete(taskId);
        }
    }

    /**
     * 流程回退到第一个节点
     *
     * @param context
     * @param request
     * @param user
     * @return
     */
    public void rollbackFirstask(String taskId, String user) {
        //移除标记REJECT的status
        taskService.removeVariable(taskId, "outcome");
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        //删除任务
        managementService.executeCommand(new TaskDeleteCmd(taskId));
        //删除变量
        managementService.executeCommand(new ExecutionVariableDeleteCmd(task.getExecutionId()));
        //将流程回滚到第一个节点
        managementService.executeCommand(new FlowToFirstCmd(task));
        System.out.println(task.getProcessInstanceId());
    }

    /**触发信号事件*/

    @Test
    public void completeSingal(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        List<Execution> executions =  processEngine.getRuntimeService().createExecutionQuery()
                .signalEventSubscriptionName("Singal")
                .list();
        for(Execution e:executions){
            processEngine.getRuntimeService().signalEventReceived("Singal", e.getId());
        }

    }
}
