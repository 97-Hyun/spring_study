package com.in28minutes.learn_spring_framework.examples.e1;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
class NormalClass {

}

// 기본적으로 스프링 프레임워크에서 생성되는 모든 Bean은 싱글톤이다.
// 하지만 다른 인스턴스를 생성하고 싶은 경우엔 @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE) 해당 어노테이션 사용하면 된다.
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component
class PrototypeClass {

}

@Configuration
@ComponentScan // 경로 지정이 없을 경우 자신이 포함된 패키지에서 자동 수행
public class BeanScopesLauncherApplication {
    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(BeanScopesLauncherApplication.class);) {
            System.out.println(context.getBean(NormalClass.class));
            System.out.println(context.getBean(NormalClass.class));
            System.out.println(context.getBean(PrototypeClass.class));
            System.out.println(context.getBean(PrototypeClass.class));
            System.out.println(context.getBean(PrototypeClass.class));
        }
    }
}
