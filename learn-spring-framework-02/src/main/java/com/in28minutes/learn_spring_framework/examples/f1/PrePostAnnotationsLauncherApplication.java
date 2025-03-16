package com.in28minutes.learn_spring_framework.examples.f1;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
class SomeClass {
    private SomeDependency someDependency;

    public SomeClass(SomeDependency someDependency) {
        super();
        this.someDependency = someDependency;
    }

    // SomeClass Bean 이 생성된 후 사용되기전에 호출된다.
    @PostConstruct
    public void init() {
        someDependency.getReady();
    }

    // SomeClass Bean 이 파괴되기전 호출된다.
    @PreDestroy
    public void cleanUp() {

    }
}

@Component
class SomeDependency {
    public void getReady() {
        System.out.println("Some logic using SomeDependency");
    }
}

@Configuration
@ComponentScan // 경로 지정이 없을 경우 자신이 포함된 패키지에서 자동 수행
public class PrePostAnnotationsLauncherApplication {
    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(PrePostAnnotationsLauncherApplication.class);) {
            Arrays.stream(context.getBeanDefinitionNames())
                    .forEach(System.out::println);
        }
    }
}
