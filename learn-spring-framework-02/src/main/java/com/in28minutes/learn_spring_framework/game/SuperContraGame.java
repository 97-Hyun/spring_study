package com.in28minutes.learn_spring_framework.game;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

// Qualifier : 자동 주입 시 필드나 매개변수에서 후보 Bean에 대한 한정자로 쓰임
// 주입 받는 곳에서도 같이 써줘야함 Primary 보다 우선시된다.
@Component
@Qualifier("SuperContraGameQualifier")
public class SuperContraGame implements GamingConsole {
    public void up() {
        System.out.println("up");
    }

    public void down() {
        System.out.println("sit down");
    }

    public void left() {
        System.out.println("go back");
    }

    public void right() {
        System.out.println("shoot a bullet");
    }
}
