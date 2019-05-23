package com.diyiliu.support.config;

import com.diyiliu.support.shiro.FormLoginFilter;
import com.diyiliu.support.shiro.UserRealm;
import com.diyiliu.support.shiro.UserSessionManager;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Description: ShiroConfig
 * Author: DIYILIU
 * Update: 2019-05-23 10:32
 */

@Configuration
public class ShiroConfig {


    @Bean
    public ShiroFilterFactoryBean shiroFilter() {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(securityManager());
        factoryBean.setLoginUrl("/console/login");
        factoryBean.setSuccessUrl("/console/home");

        Map<String, Filter> filters = new LinkedHashMap<>();
        filters.put("authc", formLoginFilter());
        factoryBean.setFilters(filters);

        Map<String, String> filterChainDefinitionMap = new LinkedHashMap();
        // swagger ui
        filterChainDefinitionMap.put("/swagger-ui.html", "anon");
        filterChainDefinitionMap.put("/swagger-resources", "anon");
        filterChainDefinitionMap.put("/v2/api-docs", "anon");
        filterChainDefinitionMap.put("/webjars/springfox-swagger-ui/**", "anon");

        filterChainDefinitionMap.put("/console/login", "anon");
        filterChainDefinitionMap.put("/console/**", "authc");
        factoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return factoryBean;
    }


    @Bean
    public FormLoginFilter formLoginFilter() {

        return new FormLoginFilter();
    }

    @Bean
    public UserRealm userRealm() {
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName("MD5");
        credentialsMatcher.setHashIterations(2);

        UserRealm userRealm = new UserRealm();
        userRealm.setCredentialsMatcher(credentialsMatcher);

        return userRealm;
    }

    @Bean
    public SessionManager sessionManager() {

        return new UserSessionManager();
    }

    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm());
        securityManager.setSessionManager(sessionManager());

        return securityManager;
    }
}
