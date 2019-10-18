package com.example.Onecloud;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest //(classes = DemoApplicationTests.class)
//@Component
public class DemoApplicationTests {

	//@Autowired
	//private RedisTemplate redisTemplate;
	//@Autowired
	//private StringRedisTemplate st;
	//@Resource
    //private RedisService ser;
	@Test
	public void contextLoads() {

	    //redisTemplate.opsForValue().set("sasd","sda");
		//setKey("name2","sadadd");
		//System.out.println(getValue("name2"));

        /*ser.setStr("age","18");
        System.out.println(ser.getStr("age"));
        Jedis jedis=new Jedis("localhost");
        jedis.auth("123456");
        jedis.set("haha","huansen");
        jedis.get("haha");
        //存储数据到列表中
        jedis.lpush("site-list", "Runoob");
        jedis.lpush("site-list", "Google");
        jedis.lpush("site-list", "Taobao");
        // 获取存储的数据并输出
        List<String> list = jedis.lrange("site-list", 0 ,2);
        for(int i=0; i<list.size(); i++) {
            System.out.println("列表项为: "+list.get(i));
        }*/
	}

/*
	public  void setKey(String key,String value){
		ValueOperations<String, String> ops = st.opsForValue();
		ops.set(key,value,1, TimeUnit.MINUTES);//1分钟过期
	}

	public String getValue(String key){
		ValueOperations<String, String> ops = this.st.opsForValue();
		return ops.get(key);
	}*/


}
