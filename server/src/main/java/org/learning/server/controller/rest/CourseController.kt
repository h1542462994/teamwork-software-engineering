package org.learning.server.controller.rest

import org.learning.server.entity.Course
import org.learning.server.form.CourseSelectForm
import org.learning.server.model.common.Response
import org.learning.server.model.common.ResponseTokens
import org.learning.server.model.common.ResponseTokens.ok
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
    lateinit var courseService: ICourseService
    private var course: String = "course"
    /**
     * 根据课程id查找课程
     * */
    @PostMapping("/selectid")
    fun selectid(@Valid courseSelectForm: CourseSelectForm, request: HttpServletRequest): Response<Course> {
        var response = courseService.selectid(courseSelectForm)
        if (response.code == ResponseTokens.ok.code) {
            //将查找到的对象存储在session作用域中
            request.session.setAttribute(course, response.data)
        }
        return courseService.selectid(courseSelectForm)


    }

}