package com.softwaretestingboard.Configurations.scope;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*creates the WebdriverThreadScopePostProcessor bean*/
@Configuration
public class WebdriverThreadScopeConfig {

    @Bean
    public static BeanFactoryPostProcessor beanFactoryPostProcessor(){
        System.out.println("Creating a WebdriverThreadScopePostProcessor Object to Register scope");
        return new WebdriverThreadScopePostProcessor();
    }

}
