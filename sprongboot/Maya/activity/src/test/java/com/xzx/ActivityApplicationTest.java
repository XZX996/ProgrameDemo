package com.xzx;

import org.activiti.dmn.api.Query;
import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
		String processDefinitionKey = "myProcess_3";
		ProcessInstance processInstance =runtimeService.startProcessInstanceByKey(processDefinitionKey);
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
}
