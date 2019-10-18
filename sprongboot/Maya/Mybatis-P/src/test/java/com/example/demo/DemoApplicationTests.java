package com.example.demo;

import com.example.demo.Dto.TestDto;
import com.example.demo.mapper.TestMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	private TestMapper tm;
	@Test
	public void contextLoads() {
	}

	@Test
	public void Test(){

		List<TestDto> td= tm.selectList(null);
		td=td.stream().filter(it->(it.getID()<8)).filter(it->(it.getID()>2)).collect(Collectors.toList());
		//td.forEach((player) -> System.out.print(player.getID() + "; "));
		td.forEach(it->System.out.println(it.getID()));

		IntSummaryStatistics ids=td.stream().mapToInt(x->x.getID()).summaryStatistics();
		System.out.println("List中最大的数字 : " + ids.getMax());
		System.out.println("List中最小的数字 : " + ids.getMin());
		System.out.println("所有数字的总和   : " + ids.getSum());
		System.out.println("所有数字的平均值 : " + ids.getAverage());


	}

}
