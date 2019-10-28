package com.example.demo.shiro;


import com.example.demo.Dao.userMapper;
import com.example.demo.common.RetryLimitCredentialsMatcher;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.io.ResourceUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
@Configuration
public class ShiroConfiguration {
    @Autowired
    private userMapper userMapper;


    @Bean
    public static LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }
    /**
     * ShiroFilterFactoryBean 处理拦截资源文件问题。
     * 注意：单独一个ShiroFilterFactoryBean配置是或报错的，因为在
     * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
     *
     Filter Chain定义说明
     1、一个URL可以配置多个Filter，使用逗号分隔
     2、当设置多个过滤器时，全部验证通过，才视为通过
     3、部分过滤器可指定参数，如perms，roles
     *
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean  = new ShiroFilterFactoryBean();

        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //拦截器.
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();
        //自定义拦截器
        Map<String, Filter> customisedFilter = new HashMap<>();
        //未登录
        customisedFilter.put("authc", new NoAuthenticationFilter());
        //权限不足
        customisedFilter.put("perms", new NoAuthorizationFilter());

        shiroFilterFactoryBean.setFilters(customisedFilter);

        List<String> sysMenus = userMapper.getAllMenus();
        Map<String, String> filterMap = FileterChainMap.getPermissionMap(sysMenus);
        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/hello/getList");
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        // setLoginUrl 如果不设置值，默认会自动寻找Web工程根目录下的"/login.jsp"页面 或 "/login" 映射
        shiroFilterFactoryBean.setLoginUrl("/login/notLogin");
        // 设置无权限时跳转的 url;
        shiroFilterFactoryBean.setUnauthorizedUrl("/login/notRole");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        System.out.println("Shiro拦截器工厂类注入成功");
        return shiroFilterFactoryBean;
    }

    /**
     * 注入 securityManager
     */
    @Bean
    @DependsOn("customRealm")
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置realm.
        securityManager.setRealm(customRealm());
        return securityManager;
    }

    /**
     * 自定义身份认证 realm;
     * <p>
     * 必须写这个类，并加上 @Bean 注解，目的是注入 CustomRealm，
     * 否则会影响 CustomRealm类 中其他类的依赖注入
     */
    @Bean
    public CustomRealm customRealm() {
        //配置自定义密码比较器
        //customRealm.setCredentialsMatcher(retryLimitHashedCredentialsMatcher());
        CustomRealm customRealm1 = new CustomRealm();
        customRealm1.setCredentialsMatcher(retryLimitHashedCredentialsMatcher());
        return  customRealm1;
    }

    /**
     * 开启shiro aop注解支持
     * 使用代理方式;所以需要开启代码支持;否则@RequiresRoles等注解无法生效
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor attributeSourceAdvisor(){
        AuthorizationAttributeSourceAdvisor attributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        attributeSourceAdvisor.setSecurityManager(securityManager());
        return attributeSourceAdvisor;
    }
    /**
     * 自动创建代理 不然AOP注解不会生效
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator autoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    /*
     * ehcache缓存管理器；shiro整合ehcache：
     * 通过安全管理器：securityManager
     * 单例的cache防止热部署重启失败
     *
     * @return EhCacheManager
     */

   /* @Bean
    public EhCacheManager ehCacheManager() {
        EhCacheManager ehcache = new EhCacheManager();
        CacheManager cacheManager = CacheManager.getCacheManager("shiro");
        if (cacheManager == null) {
            try {
                cacheManager = CacheManager.create(ResourceUtils.getInputStreamForPath("classpath:ehcache.xml"));
            } catch (CacheException | IOException e) {
                e.printStackTrace();
            }
        }
        ehcache.setCacheManager(cacheManager);
        return ehcache;
    }*/
   @Bean
   public EhCacheManager ehCacheManager(){
       EhCacheManager cacheManager = new EhCacheManager();
       cacheManager.setCacheManagerConfigFile("classpath:ehcache.xml");
       return cacheManager;
   }
    /**
     * 配置密码比较器
     * @return
     */
    @Bean("credentialsMatcher")
    public RetryLimitCredentialsMatcher retryLimitHashedCredentialsMatcher(){
        RetryLimitCredentialsMatcher retryLimitCredentialsMatcher = new RetryLimitCredentialsMatcher(ehCacheManager());

        //如果密码加密,可以打开下面配置
        //加密算法的名称
        retryLimitCredentialsMatcher.setHashAlgorithmName("MD5");
        //配置加密的次数
        retryLimitCredentialsMatcher.setHashIterations(1);
        //是否存储为16进制
        //retryLimitCredentialsMatcher.setStoredCredentialsHexEncoded(true);
        return retryLimitCredentialsMatcher;
    }
}
