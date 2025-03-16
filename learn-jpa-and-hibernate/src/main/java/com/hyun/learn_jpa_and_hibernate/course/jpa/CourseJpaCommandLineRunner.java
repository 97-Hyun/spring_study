//package com.hyun.learn_jpa_and_hibernate.course.jpa;
//
//import com.hyun.learn_jpa_and_hibernate.course.Course;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//public class CourseJpaCommandLineRunner implements CommandLineRunner {
//
//    @Autowired
//    private CourseJpaRepository repository;
//
//    @Override
//    public void run(String... args) throws Exception {
//        repository.insert(new Course(1, "Learn AWS Now!", "hyun"));
//        repository.insert(new Course(2, "Learn Azure Now!", "hyun"));
//        repository.insert(new Course(3, "Learn DevOps Now!", "hyun"));
//
//        repository.deleteById(1);
//
//        System.out.println(repository.findById(2));
//    }
//}
