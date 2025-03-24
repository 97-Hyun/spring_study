package com.hyun.learn_spring_aop.aspects;

import org.aspectj.lang.annotation.Pointcut;

public class CommonPointcutConfig {

    @Pointcut("execution(* com.hyun.learn_spring_aop.*.*.*(..))")
    public void businessAndDataPackageConfig() {

    }
}
