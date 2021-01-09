package org.learning.server.service.impl

import org.learning.server.entity.Course
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
}