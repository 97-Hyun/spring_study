package com.in28minutes.learn_spring_framework.helloworld;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App02HelloWorldSpring {
    public static void main(String[] args) {
        // 스프링 컨텍스트 실행 (JVM 내에 스프링 컨텍스트 생성)
        var context = new AnnotationConfigApplicationContext(HelloWorldConfiguration.class);

        System.out.println(context.getBean("name"));
        System.out.println(context.getBean("person"));
        System.out.println(context.getBean("new_person"));
    }
}
