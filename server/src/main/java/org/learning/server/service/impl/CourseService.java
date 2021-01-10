package org.learning.server.service.impl;

import org.learning.server.entity.Course;
import org.learning.server.form.CourseSelectForm;
import org.learning.server.model.common.Response;
import org.learning.server.model.common.Responses;
import org.learning.server.repository.CourseRepository;
import org.learning.server.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService implements ICourseService {
    private CourseRepository courseRepository;
    @Autowired
    public void setCourseRepository(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }
    @Override
    public Response<Course> selectid(CourseSelectForm course){
        Optional<Course> dbCourse=courseRepository.findById(course.getId());
        return Responses.ok(dbCourse.get());

    }

    /*@Override
    public Response<Course> selectall() {
        List<Course> dbCourse=courseRepository.findAll();
        return Responses.ok(dbCourse);
    }*/



}
