package org.learning.server.controller.rest

import org.learning.server.common.SessionHelper
import org.learning.server.entity.CourseOpen
import org.learning.server.model.common.Response
import org.learning.server.model.common.Responses
import org.learning.server.model.complex.CourseOpenInfo
import org.learning.server.service.ICourseOpenService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/api/course_open")
class CourseOpenController {
    @Autowired
    lateinit var courseOpenService: ICourseOpenService
    /**
     * 开设一门课程
     */
    @PostMapping("/create")
    fun create(orgId: Int, courseId: Int, request: HttpServletRequest): Response<CourseOpen> {
        val user = SessionHelper.of(request).user()!!
        return courseOpenService.create(orgId, courseId, user)
    }

    /**
     * 获取所有与用户有关的开设的课程
     */
    @PostMapping("/list")
    fun list(request: HttpServletRequest): Response<Iterable<CourseOpenInfo>> {
        val user = SessionHelper.of(request).user()!!
        return Responses.ok(courseOpenService.list(user))
    }

    /**
     * 所有所有与组织相关的开设的课程
     */
    @PostMapping("/list/org")
    fun listOfOrgNode(orgId: Int, request: HttpServletRequest): Response<Iterable<CourseOpenInfo>> {
        val user = SessionHelper.of(request).user()!!
        return Responses.ok(courseOpenService.listOfOrgNode(orgId, user))
    }
}