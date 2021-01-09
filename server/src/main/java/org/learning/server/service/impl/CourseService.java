package org.learning.server.service.impl;

import org.learning.server.entity.Course;
import org.learning.server.form.CoursePublishForm;
import org.learning.server.model.ActionResult;
import org.learning.server.repository.CourseRepository;
import org.learning.server.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CourseService implements ICourseService {

    private CourseRepository courseRepository;

    @Autowired
    public void setCourseRepository(CourseRepository courseRepository){this.courseRepository=courseRepository;}

    @Override
    public ActionResult<Course> addCourse(CoursePublishForm course) {
        ActionResult<Course> actionResult=new ActionResult<>();
        if(course.getId()==null){
            return actionResult.error("课程编号不能为空");
        }
        Course formCourse=course.toCourse();
        //get the course in db
        Optional<Course> dbCourse=courseRepository.findById(course.getId());
        if(dbCourse.isPresent()){
            return actionResult.error("不能添加重复的课程编号");
        }
        courseRepository.save(formCourse);
        return actionResult.value(formCourse).success();

    }

    @Override
    public boolean deleteCourseById(Integer id){
        return courseRepository.deleteCourseById(id)>0;
    }


}
