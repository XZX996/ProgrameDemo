package com.example.demo;

import com.example.demo.server.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
@Component
public class DemoApplicationTests {

	@Autowired
	private RedisTemplate redisTemplate;
	@Autowired
	private StringRedisTemplate st;
	@Resource
    private RedisService ser;
	@Test
	public void contextLoads() {
	    redisTemplate.opsForValue().set("sasd","sda");
		System.out.println(redisTemplate.opsForValue().get("sasd"));
		setKey("name2","sadadd");
		System.out.println(getValue("name2"));
        ser.setStr("age","18");
        System.out.println(ser.getStr("age"));
        Jedis jedis=new Jedis("localhost");
        jedis.auth("123456");
        jedis.set("haha","huangsen");
        jedis.get("haha");
        //存储数据到列表中
        jedis.lpush("site-list", "Runoob");
        jedis.lpush("site-list", "Google");
        jedis.lpush("site-list", "Taobao");
        // 获取存储的数据并输出
        List<String> list = jedis.lrange("site-list", 0 ,2);
        for(int i=0; i<list.size(); i++) {
            System.out.println("列表项为: "+list.get(i));
        }
	}

	public  void setKey(String key,String value){
		ValueOperations<String, String> ops = st.opsForValue();
		ops.set(key,value,1, TimeUnit.MINUTES);//1分钟过期
	}

	public String getValue(String key){
		ValueOperations<String, String> ops = this.st.opsForValue();
		return ops.get(key);
	}


}
