package com.in28minutes.learn_spring_framework.helloworld;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// record
// jdk 16 에서 추가된 새로운 기능
// 기존 클래스에 생성해야하는 equals, 생성자, getter, setter 등을 자동으로 만들어준다
record Person (String name, int age) {};

@Configuration
public class HelloWorldConfiguration {

    // Bean = 스프링이 관리하는 객체
    @Bean
    public String name() {
        return "Ranga";
    }

    @Bean
    public int age() {
        return 15;
    }

    @Bean
    public Person person() {
        return new Person("Ravi", 20);
    }

    @Bean(name = "new_person")
    public Person newPerson() {
        return new Person(name(), age());
    }
}
