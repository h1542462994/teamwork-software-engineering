package org.learning.server.service.impl

import org.learning.server.entity.Course
import org.learning.server.form.CoursePublishForm
import org.learning.server.model.ActionResult
import org.learning.server.model.common.Response
import org.learning.server.model.common.Responses
import org.learning.server.repository.CourseRepository
import org.learning.server.service.ICourseService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CourseService : ICourseService {
    @Autowired
    lateinit var courseRepository: CourseRepository

    override fun findAll(): Iterable<Course> {
        return courseRepository.findAll();
    }

    override fun create(courseForm: CoursePublishForm): Response<Course> {
        var course = courseForm.toCourse()
        //get the course in db
        course = courseRepository.save(course)
        return Responses.ok(course)
    }

    override fun delete(id: Int): Boolean {
        courseRepository.deleteById(id)
        return true
    }
}