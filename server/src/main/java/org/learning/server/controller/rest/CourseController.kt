package org.learning.server.controller.rest

import org.learning.server.entity.Course
import org.learning.server.form.CoursePublishForm
import org.learning.server.model.ActionResult
import org.learning.server.model.common.Response
import org.learning.server.model.common.ResponseToken
import org.learning.server.model.common.ResponseTokens
import org.learning.server.model.common.Responses
import org.learning.server.service.ICourseService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest
import javax.validation.Valid

@RestController
@RequestMapping("/api/course")
class CourseController {
    @Autowired
    lateinit var ICourseService:ICourseService

    /**
     * 添加新课程
     * */

    @PostMapping("/addCourse")
    fun addCourse(@Valid coursePublishForm: CoursePublishForm, request: HttpServletRequest): ActionResult<Course>? {
        var response=ICourseService.addCourse(coursePublishForm)
        return ICourseService.addCourse(coursePublishForm);
    }
}