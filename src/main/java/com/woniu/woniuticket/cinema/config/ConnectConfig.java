package com.woniu.woniuticket.cinema.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ConnectConfig implements WebMvcConfigurer {


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/welcome").setViewName("welcome");
        /*registry.addViewController("/addScreening").setViewName("addScreening");*/
/*        registry.addViewController("/login").setViewName("login");*/
/*        registry.addViewController("/order-list").setViewName("order-list");*/
    }
}
