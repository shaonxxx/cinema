package com.woniu.woniuticket.cinema.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ConnectConfig implements WebMvcConfigurer {


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/welcome").setViewName("welcome");
        registry.addViewController("/addHall").setViewName("addHall");
        registry.addViewController("/member-list").setViewName("member-list");
        registry.addViewController("/member-list1").setViewName("member-list1");
        registry.addViewController("/order-list").setViewName("order-list");
        registry.addViewController("/order-list1").setViewName("order-list1");
        registry.addViewController("/film-list").setViewName("film-list");
        /*registry.addViewController("/addScreening").setViewName("addScreening");*/
/*        registry.addViewController("/login").setViewName("login");*/
/*        registry.addViewController("/order-list").setViewName("order-list");*/
    }
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .maxAge(3600);
            }
}
