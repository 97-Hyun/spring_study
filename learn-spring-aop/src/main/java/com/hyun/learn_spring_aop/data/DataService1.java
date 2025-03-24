package com.hyun.learn_spring_aop.data;

import org.springframework.stereotype.Repository;

@Repository
public class DataService1 {

    public int[] getData() {
        return new int[] {11, 22, 33, 44, 55};
    }

}
