package com.in28minutes.learn_spring_framework.examples.c1;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@ComponentScan // 경로 지정이 없을 경우 자신이 포함된 패키지에서 자동 수행
public class RealWorldSpringContextLauncherApplication {
    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(RealWorldSpringContextLauncherApplication.class);) {
            Arrays.stream(context.getBeanDefinitionNames())
                    .forEach(System.out::println);

            System.out.println(context.getBean(BusinessCalculationService.class).findMax());
        }
    }
}
