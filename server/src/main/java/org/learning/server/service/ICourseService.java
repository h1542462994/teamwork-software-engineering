package org.learning.server.service;

import org.learning.server.entity.Course;
import org.learning.server.form.CoursePublishForm;
import org.learning.server.model.ActionResult;

import javax.persistence.criteria.CriteriaBuilder;

public interface ICourseService {
    ActionResult<Course> addCourse(CoursePublishForm course);
    boolean deleteCourseById(Integer id);

}
