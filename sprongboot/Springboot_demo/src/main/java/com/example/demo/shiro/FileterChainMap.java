package com.example.demo.shiro;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FileterChainMap {

static Logger logger = LoggerFactory.getLogger(FileterChainMap.class);

    /**
     * 动态更新新的权限
     *
     * @param filterMap
     */
    public synchronized static void updatePermission(ShiroFilterFactoryBean myShiroFilterFactoryBean, Map<String, String> filterMap) {

        AbstractShiroFilter shiroFilter = null;
        try {
            shiroFilter = (AbstractShiroFilter) myShiroFilterFactoryBean.getObject();

            // 获取过滤管理器
            PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver) shiroFilter
                    .getFilterChainResolver();
            DefaultFilterChainManager filterManager = (DefaultFilterChainManager) filterChainResolver.getFilterChainManager();

            //清空拦截管理器中的存储
            filterManager.getFilterChains().clear();
            /*
            清空拦截工厂中的存储,如果不清空这里,还会把之前的带进去
            ps:如果仅仅是更新的话,可以根据这里的 map 遍历数据修改,重新整理好权限再一起添加
             */
            myShiroFilterFactoryBean.getFilterChainDefinitionMap().clear();

            // 相当于新建的 map, 因为已经清空了
            Map<String, String> chains = myShiroFilterFactoryBean.getFilterChainDefinitionMap();
            //把修改后的 map 放进去
            chains.putAll(filterMap);

            //这个相当于是全量添加
            for (Map.Entry<String, String> entry : filterMap.entrySet()) {
                //要拦截的地址
                String url = entry.getKey().trim().replace(" ", "");
                //地址持有的权限
                String chainDefinition = entry.getValue().trim().replace(" ", "");
                //生成拦截
                filterManager.createChain(url, chainDefinition);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("updatePermission error,filterMap=" + filterMap, e);
        }

        myShiroFilterFactoryBean.getFilterChainDefinitionMap();
    }

    public static Map<String, String> getPermissionMap(List<String> sysMenus) {
        Map<String, String> filterMap = new LinkedHashMap<>();
        //游客，开发权限
        filterMap.put("/guest/**", "anon");
        //开放登陆接口
        filterMap.put("/login/login", "anon");
        filterMap.put("/login/Unlock", "anon");
        //对swagger开放
        filterMap.put("/swagger", "anon");
        filterMap.put("/swagger-ui.html", "anon");
        filterMap.put("/swagger/**", "anon");
        filterMap.put("/swagger-resources/**", "anon");
        filterMap.put("/v2/**", "anon");
        filterMap.put("/webjars/**", "anon");
        filterMap.put("/configuration/**", "anon");

        /*for (String item : sysMenus) {
            if(item !=null && item !="") {
                filterMap.put(item, "perms[" + item + "]");
            }
            *//*if (item.getState().equals("1")) {
                if (item.getService() != null && item.getService().equals("stardon_main")) {
                    filterMap.put("/" + item.getController() + "/" + item.getAction(), "perms[/" + item.getController() + "/" + item.getAction() + "]");
                    // logger.info("===>perms[/" + item.getController() + "/" + item.getAction() + "]");
                } else {
                    filterMap.put("/" + item.getService() + "/" + item.getController() + "/" + item.getAction(), "perms[/" +item.getService()+"/"+ item.getController() + "/" + item.getAction() + "]");
                    //logger.info("===>perms[/" + item.getService() + "/" + item.getController() + "/" + item.getAction() + "]");
                }
            }*//*
        }*/
        //其余接口一律拦截
        //主要这行代码必须放在所有权限设置的最后，不然会导致所有 url 都被拦截
        filterMap.put("/**", "authc");
        return filterMap;
    }

}

