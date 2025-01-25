package me.fany.shiro.config;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.LinkedHashMap;
import java.util.Properties;

@Configuration
public class ShiroConfig {
    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        System.out.println("shirFilter ...");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 拦截器
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();

        // 配置不会被拦截的url
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/logout", "logout");
//        filterChainDefinitionMap.put("/admin/**", "authc");
        filterChainDefinitionMap.put("/admin/*", "authc");
        filterChainDefinitionMap.put("/admin/info", "anon");
        filterChainDefinitionMap.put("/**", "anon");

        // 如果不设置默认会自动寻找web工程根目录下的“/login.jsp”
        shiroFilterFactoryBean.setLoginUrl("/login");

        //  登录成功后要跳转的连接
        shiroFilterFactoryBean.setSuccessUrl("/index");

        // 未授权页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;

    }

    /*
        凭证匹配器
     */

    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        hashedCredentialsMatcher.setHashIterations(2);
        return hashedCredentialsMatcher;
    }


    @Bean
    public MyShiroRealm myShiroRealm() {
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return myShiroRealm;
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        return securityManager;
    }

    /*
        开启shiro aop注解支持
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean(name = "simpleMappingExceptionResolver")
    public SimpleMappingExceptionResolver createsimpleMappingExceptionResolver() {
        SimpleMappingExceptionResolver r = new SimpleMappingExceptionResolver();
        Properties mappings = new Properties();
        mappings.setProperty("UnauthorizedException", "403");
        mappings.setProperty("DatabaseException", "databaseError");
        r.setExceptionMappings(mappings);
        r.setDefaultErrorView("error");
        r.setExceptionAttribute("ex");
        return r;
    }
}
