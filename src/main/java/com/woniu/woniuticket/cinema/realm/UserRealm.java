package com.woniu.woniuticket.cinema.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserRealm extends AuthorizingRealm {

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        String subject= (String) authenticationToken.getPrincipal();
        System.out.println("===================="+subject);
        if(subject==null){
            return null;
        }
        /*if(!subject.equals("123")){
            throw null;
        }*/
        Object password="321";

        SimpleAuthenticationInfo authenticationInfo=new SimpleAuthenticationInfo(subject,password,this.getName());

        return authenticationInfo;
    }

   /* private String getName() {
    }*/
}
