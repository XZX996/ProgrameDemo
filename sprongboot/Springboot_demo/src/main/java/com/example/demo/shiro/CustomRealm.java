package com.example.demo.shiro;

import com.example.demo.Dao.userMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;


public class CustomRealm extends AuthorizingRealm {

    private userMapper userMapper;

    @Autowired
    private void setUserMapper(userMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * 获取身份验证信息
     * Shiro中，最终是通过 Realm 来获取应用程序中的用户、角色及权限信息的。
     *
     * @param authenticationToken 用户身份信息 token
     * @return 返回封装了用户信息的 AuthenticationInfo 实例
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("————身份认证方法————");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        String username = token.getUsername();
        //String password1 = String.valueOf(token.getPassword());
        // 从数据库获取对应用户名密码的用户
        String password = userMapper.getPassword(token.getUsername());
        String pp=new String((char[]) token.getCredentials());
        String passwordEncoded = new SimpleHash("md5",pp).toString();
        if (null == password) {
            throw new UnknownAccountException ("用户名不正确");
        } else if (!password.equals(passwordEncoded)) {
            throw new IncorrectCredentialsException ("密码不正确");
        }
        //Object ss=token.getPrincipal();
        //第一个参数就是我们需要在保存在shiro中的session中的对象，
        // 注入第二参数是从数据库中查询出来的正确的密码，shiro会自动判断，如果此密码和刚才传递的密码不一致会上抛异常
        //第三个参数是盐，
        //第四个参数是自定义的realm的名字，改方法可以重写自己随意更改
        SimpleAuthenticationInfo simpleAuthenticationInfo= new SimpleAuthenticationInfo(username, pp, getName());
        return simpleAuthenticationInfo;
    }

    /**
     * 获取授权信息
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("————权限认证————");
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //获得该用户角色
        String role = userMapper.getRole(username);
        Set<String> set = new HashSet<>();
        //需要将 role 封装到 Set 作为 info.setRoles() 的参数
        set.add(role);
        //设置该用户拥有的角色
        info.setRoles(set);
        return info;
    }

}
