package com.softwaretestingboard.Configurations.scope;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/*WebdriverThreadScope is not registered by default
so here we explicitly register it through ConfigurableBeanFactory.registerScope*/
public class WebdriverThreadScopePostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("REGISTERING SCOPE");
        beanFactory.registerScope("WebdriverThreadScope", new WebdriverThreadScope());
    }

}
