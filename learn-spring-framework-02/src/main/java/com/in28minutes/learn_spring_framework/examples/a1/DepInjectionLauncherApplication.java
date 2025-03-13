package com.in28minutes.learn_spring_framework.examples.a1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Arrays;

// 의존성 주입 3가지 유형
@Component
class YourBusinessClass {
    // 필드 주입
//    @Autowired
    Dependency1 dependency1;
    Dependency2 dependency2;

    // 생성자 주입
    public YourBusinessClass(Dependency1 dependency1, Dependency2 dependency2) {
        this.dependency1 = dependency1;
        this.dependency2 = dependency2;
    }

    // Setter 주입
    //    @Autowired
//    public void setDependency1(Dependency1 dependency1) {
//        this.dependency1 = dependency1;
//    }
//
//    @Autowired
//    public void setDependency2(Dependency2 dependency2) {
//        this.dependency2 = dependency2;
//    }

    public String toString() {
        return "Using "+dependency1+" and "+dependency2;
    }
}

@Component
class Dependency1 {

}

@Component
class Dependency2 {

}

@Configuration
@ComponentScan // 경로 지정이 없을 경우 자신이 포함된 패키지에서 자동 수행
public class DepInjectionLauncherApplication {
    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(DepInjectionLauncherApplication.class);) {
            Arrays.stream(context.getBeanDefinitionNames())
                    .forEach(System.out::println);

            System.out.println(context.getBean(YourBusinessClass.class));
        }
    }
}
