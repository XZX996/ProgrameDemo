package com.example.demo.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RedisService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Resource(name="stringRedisTemplate")
    private ValueOperations<String,String> valopstr;

    @Autowired
    private RedisTemplate redisTemplate;
    @Resource(name="redisTemplate")
    private ValueOperations<Object,Object> valopobj;

    /**
     * 根据指定key获取String
     * @param key
     * @return
     */
    public String getStr(String key){
        String s = valopstr.get(key);

        if(s==null){
            return "暂无对应的值";
        }else{
            return s;
        }
    }
    /**
     * 设置Str缓存
     * @param key
     * @param val
     */
    public void setStr(String key, String val){
        valopstr.set(key,val);

    }
    /**
     * 删除指定key
     * @param key
     */

    public void del(String key){
        stringRedisTemplate.delete(key);
    }
    /**
     * 根据指定o获取Object
     * @param o
     * @return
     */
    public Object getObj(Object o) {
        Object o1 = valopobj.get(o);
        if(o1==null){
            return o1;
        }else{
            return o1;
        }
    }

    /**
     * 设置obj缓存
     * @param key
     * @param value
     */
    public void setObj(Object key, Object value){

        valopobj.set(key, value);
    }

    /**
     * 删除Obj缓存
     * @param o
     */
    public void delObj(Object o){
        redisTemplate.delete(o);
    }


    /** 添加对象到redis 里面的list中
     *  redis中的 list 是双向的 所以添加的时候需要注意
     *  rightPush 先进先出 leftPush 先进后出 这里 需要注意
     * @param key list 对应的key
     * @param obj 需要存的对象
     */
    public void addList(String key,Object obj){
        redisTemplate.opsForList().rightPush(key,obj);
    }


    /**
     * opsForList().range(key, start, end);  取范围值  redis里面的list下标从0开始
     *  流程 拿到key 对应的list 取 0 到 5  和 mysql的limt  类似 注意下标即可
     * 从redis list 里面的获取数据分页
     * @param key redis list 对应的key
     * @param start  开始下标
     * @param end 介绍下标
     * @return 返回list给前端
     */
    public List getListPage(String key, int start, int end){
        return (List)redisTemplate.opsForList().range(key, start, end);
    }
}
