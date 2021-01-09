package org.learning.server.controller.rest

import org.learning.server.common.SessionHelper
import org.learning.server.entity.Course
import org.learning.server.form.CourseForm
import org.learning.server.model.common.Response
import org.learning.server.model.common.Responses
import org.learning.server.service.impl.CourseService
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
    lateinit var courseService: CourseService

    /**
     * 仅供测试，查找所有课程
     */
    @PostMapping("/all")
    fun all(): Response<Iterable<Course>> {
        return Responses.ok(courseService.all())
    }

    /**
     * 添加新课程
     * */

    @PostMapping("/create")
    fun create(@Valid courseForm: CourseForm, request: HttpServletRequest): Response<Course> {
        val user = SessionHelper.of(request).user()!!
        return courseService.create(courseForm)
    }
}