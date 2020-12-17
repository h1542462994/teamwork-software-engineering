package org.learning.server.service;

import org.learning.server.model.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    void saveOrUpdate(Course instance);

    List<Course> getCourses();

    Optional<Course> getCourse(Integer id);

    boolean delete(Course instance);
}
