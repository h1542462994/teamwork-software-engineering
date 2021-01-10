package org.learning.server.service;

import org.learning.server.entity.Course;
import org.learning.server.form.CourseSelectForm;
import org.learning.server.model.common.Response;

public interface ICourseService {
    Response<Course> selectid(CourseSelectForm course);
    /*Response<Course> selectall();*/

}
