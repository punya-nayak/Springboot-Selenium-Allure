package com.softwaretestingboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication //equivalent of using @EnableAutoConfiguration, @ComponentScan and @SpringBootConfiguration
//@EntityScan
@ComponentScan(basePackages = {"com.softwaretestingboard"})
public class CucumberSpringConfiguration {
    public static void main(String[] args) {
        System.out.println("In main method.");
        ApplicationContext context = SpringApplication.run(CucumberSpringConfiguration.class, args);
    }
}
