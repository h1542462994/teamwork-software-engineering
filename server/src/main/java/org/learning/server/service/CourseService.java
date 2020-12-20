package org.learning.server.service;


import org.learning.server.entity.Course;
import org.learning.server.entity.CourseNode;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    void saveOrUpdate(Course instance);

    List<Course> getCourses();

    Optional<Course> getCourse(Integer id);

    boolean delete(Course instance);

    List<Course> findCoursesByName(String name);
    List<CourseNode> findCnodeById(Integer id);
}
