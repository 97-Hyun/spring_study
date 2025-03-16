package com.hyun.learn_jpa_and_hibernate.course.spring_jpa;

import com.hyun.learn_jpa_and_hibernate.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseSpringDataJpaRepository extends JpaRepository<Course, Long> {

    List<Course> findByAuthor(String author);

}
