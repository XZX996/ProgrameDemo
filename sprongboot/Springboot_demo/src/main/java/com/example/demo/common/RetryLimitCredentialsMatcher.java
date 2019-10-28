package com.example.demo.common;

import com.example.demo.Dao.userMapper;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 统计登录失败次数
 */
//HashedCredentialsMatcher

public class RetryLimitCredentialsMatcher extends HashedCredentialsMatcher {
    private static final Logger log = LoggerFactory.getLogger(RetryLimitCredentialsMatcher.class);

    @Autowired
    private userMapper userMapper;


    private int MAX_LOGIN_RETRY_TIMES = 5;
    private Cache<String, AtomicInteger> passwordRetryCache;

    //设置最大次数
    public void setMaxRetryNum(int maxRetryNum) {
        this.MAX_LOGIN_RETRY_TIMES = maxRetryNum;
    }
    public RetryLimitCredentialsMatcher(EhCacheManager ehCacheManager) {
        passwordRetryCache = ehCacheManager.getCache("passwordRetryCache");
        System.out.println("自定义登录次数验证加载");
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        System.out.println("开始验证次数");
        //获取用户名
        String username = (String)token.getPrincipal();
        Object credentials = token.getCredentials();
        //获取用户登录次数
        AtomicInteger retryCount = passwordRetryCache.get(username);
        if (retryCount == null) {
            //如果用户没有登陆过,登陆次数加1 并放入缓存
            retryCount = new AtomicInteger(1);
            passwordRetryCache.put(username, retryCount);
        }
        Map map = userMapper.getCurrentUser(username);
        if (retryCount.incrementAndGet() > MAX_LOGIN_RETRY_TIMES) {
            map.put("ISLOCK",'1');
            userMapper.updateUser(map);
            log.warn("用户[{}]进行登录验证..失败验证超过{}次,账户锁定60分钟", username, MAX_LOGIN_RETRY_TIMES);
            throw new ExcessiveAttemptsException("username: " + username + " 短期内尝试登录密码或用户名错误三次！账户锁定60分钟！");
        }
        boolean matches = super.doCredentialsMatch(token, info);
        if (matches) {
            //clear retry data
            passwordRetryCache.remove(username);
        }
        return matches;
    }
    /**
     * 根据用户名 解锁用户
     * @param user
     * @return
     */
    public Boolean unlockAccount(Map user){
        if( userMapper.updateUser(user)>0) {
            passwordRetryCache.remove(user.get("LOGINNAME").toString());
            return true;
        }else {
            return false;
        }
    }
}
