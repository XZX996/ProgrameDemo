package com.xzx;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivityApplicationTest {

	@Test
	public void contextLoads() {
		System.out.println("dssdsds");
	}

	@Test
	public void deploy() {
		//得到流程引擎的方式三，利用底层封装，来加载配置文件，只需要调用方法即可
		ProcessEngine pec = ProcessEngines.getDefaultProcessEngine();

		//部署的服务对象
		RepositoryService repositoryService = pec.getRepositoryService();

		//部署请假任务
		Deployment deploy = repositoryService.createDeployment()
				.addClasspathResource("process/qingjia.xml")
				.name("请假")
				.deploy();

		System.out.println("请假部署ID：" + deploy.getId());
	}
}
