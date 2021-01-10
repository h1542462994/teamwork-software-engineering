package org.learning.server.repository;

import org.learning.server.entity.Course;
import org.learning.server.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CourseRespository extends CrudRepository<Course, Integer> {
    Optional<Course> findById(Integer id);
    /*接口类型怎么写*/
    @Override
    Iterable<Course> findAll();
}
