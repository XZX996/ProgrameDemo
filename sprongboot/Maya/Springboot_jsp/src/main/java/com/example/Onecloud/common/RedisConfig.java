package com.example.Onecloud.common;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Repository;

@Configuration
@EnableCaching
@Repository
public class RedisConfig {

    @Bean
    public RedisCacheManager cacheManager (RedisConnectionFactory connectionFactory){
        return RedisCacheManager.create(connectionFactory);
    }
    @Bean
    public RedisTemplate<Object,Object> redisTemplate(RedisConnectionFactory connectionFactory){
        RedisTemplate<Object,Object> template=new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        //序列化
        Jackson2JsonRedisSerializer serializer=new Jackson2JsonRedisSerializer(Object.class);

        ObjectMapper obj=new ObjectMapper();
        obj.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        obj.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        serializer.setObjectMapper(obj);

        template.setValueSerializer(serializer);
        //序列化值
        template.setKeySerializer(new StringRedisSerializer());
        template.afterPropertiesSet();
        //添加事务
        template.setEnableTransactionSupport(true);
        return template;
    }

    @Bean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory connectionFactory){
        StringRedisTemplate stringRedisTemplate =new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(connectionFactory);
        stringRedisTemplate.setEnableTransactionSupport(true);
        return stringRedisTemplate;
    }
}

