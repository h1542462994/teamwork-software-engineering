package org.learning.server.repository;

import org.learning.server.entity.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course,Integer> {
    Integer deleteCourseById(Integer id);

}
