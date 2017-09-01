package com.profileExample;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        //Enable a "live" profile
        context.getEnvironment().setActiveProfiles("live");
        context.register(AppConfig.class);
        context.refresh();

        ((ConfigurableApplicationContext) context).close();

    }
}
