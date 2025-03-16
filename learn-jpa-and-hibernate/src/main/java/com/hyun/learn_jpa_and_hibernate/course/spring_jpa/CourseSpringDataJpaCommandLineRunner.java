package com.hyun.learn_jpa_and_hibernate.course.spring_jpa;

import com.hyun.learn_jpa_and_hibernate.course.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CourseSpringDataJpaCommandLineRunner implements CommandLineRunner {

    @Autowired
    private CourseSpringDataJpaRepository repository;

    @Override
    public void run(String... args) throws Exception {
        repository.save(new Course(1, "Learn AWS Now!", "hyun"));
        repository.save(new Course(2, "Learn Azure Now!", "hyun"));
        repository.save(new Course(3, "Learn DevOps Now!", "hyun"));

        repository.deleteById(1L);

        System.out.println(repository.findById(2L));
        System.out.println(repository.findByAuthor("hyun"));
        System.out.println(repository.findByAuthor(""));
    }
}
