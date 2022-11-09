package com.projectbuddy2.security;

import com.projectbuddy2.controller.ViewController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


@EnableWebMvc
@ComponentScan("org.springframework.security.samples.mvc")
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/login").setViewName("login");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver bean = new InternalResourceViewResolver();

        bean.setViewClass(ViewController.class);
        bean.setPrefix("/templates/frontend/");
        bean.setSuffix(".html");

        return bean;
    }
}
