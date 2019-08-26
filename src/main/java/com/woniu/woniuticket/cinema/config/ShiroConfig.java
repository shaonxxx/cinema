package com.woniu.woniuticket.cinema.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.woniu.woniuticket.cinema.realm.UserRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean factoryBean=new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(securityManager);
        factoryBean.setSuccessUrl("/index");
        factoryBean.setUnauthorizedUrl("/refused");
        factoryBean.setLoginUrl("/login");
        LinkedHashMap map=new LinkedHashMap();
        map.put("/static/**","anon");
        map.put("/login.do","anon");
        map.put("/login","anon");
        map.put("/login.html","anon");
        map.put("/css/**","anon");
        map.put("/fonts/**","anon");
        map.put("/font/**","anon");
        map.put("/images/**","anon");
        map.put("/js/**","anon");
        map.put("/layui/**","anon");
        map.put("/modules/**","anon");
        map.put("/logout","logout");
        map.put("/*.png","logout");
        map.put("/*.jpg","logout");
//        map.put("/static/*.jpg","anon");
//        map.put("/static/*.png","anon");
        map.put("/**","authc");
        factoryBean.setFilterChainDefinitionMap(map);
        return factoryBean;
    }

    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher=new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashIterations(2);
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        return hashedCredentialsMatcher;
    }

    @Bean
    public MemoryConstrainedCacheManager memoryConstrainedCacheManager(){
        MemoryConstrainedCacheManager memoryConstrainedCacheManager=new MemoryConstrainedCacheManager();
        return memoryConstrainedCacheManager;
    }

    @Bean(name="authorizer")
    public UserRealm userRealm(MemoryConstrainedCacheManager cacheManager, HashedCredentialsMatcher matcher){//
        UserRealm userRealm=new UserRealm();
//        userRealm.setCredentialsMatcher(matcher);
//        userRealm.setCacheManager(cacheManager);
        return userRealm;
    }

    @Bean
    public DefaultWebSecurityManager defaultSecurityManager(UserRealm userRealm){
        DefaultWebSecurityManager defaultSecurityManager=new DefaultWebSecurityManager();
        defaultSecurityManager.setRealm(userRealm);
        return defaultSecurityManager;
    }
    @Bean
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }




}
