package com.hyun.learn_jpa_and_hibernate.course.jpa;

import com.hyun.learn_jpa_and_hibernate.course.Course;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class CourseJpaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void insert(Course course) {
        entityManager.merge(course);
    }

    public void deleteById(long id) {
        Course c = entityManager.find(Course.class, id);
        entityManager.remove(c);
    }

    public Course findById(long id) {
        return entityManager.find(Course.class, id);
    }
}
