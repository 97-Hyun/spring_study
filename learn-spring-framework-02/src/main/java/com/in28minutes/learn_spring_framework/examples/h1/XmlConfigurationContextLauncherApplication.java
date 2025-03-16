package com.in28minutes.learn_spring_framework.examples.h1;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

// 이와 같이 기존 @어노테이션 및 자바 코드를 활용하여 스프링 프레임워크를 구성한 것을 기존에는 xml 파일에 Bean, Component Scan 등을 활용 했었다.
// 현재는 어노테이션과 자바 코드를 많이 사용.
// 기존 레거시 프로젝트들은 xml 파일을 사용하는 곳도 있으니 알아두면 좋다.
public class XmlConfigurationContextLauncherApplication {
    public static void main(String[] args) {
        try (var context = new ClassPathXmlApplicationContext("contextConfiguration.xml")) {
            Arrays.stream(context.getBeanDefinitionNames())
                    .forEach(System.out::println);

            System.out.println(context.getBean("name"));
        }
    }
}
