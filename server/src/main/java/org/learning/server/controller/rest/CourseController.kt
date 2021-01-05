package org.learning.server.controller.rest

import org.learning.server.entity.Course
import org.learning.server.model.common.Response
import org.learning.server.model.common.Responses
import org.learning.server.service.ICourseService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/course")
class CourseController {
    @Autowired
    lateinit var courseService: ICourseService

    @PostMapping("/all")
    fun all(): Response<Iterable<Course>> {
        return Responses.ok(courseService.findAll())
    }
}