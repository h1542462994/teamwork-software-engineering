package org.learning.server.service

import org.learning.server.entity.Course
import org.learning.server.form.CoursePublishForm
import org.learning.server.model.common.Response

interface ICourseService {
    fun findAll(): Iterable<Course>
    fun create(course: CoursePublishForm): Response<Course>
    fun delete(id: Int): Boolean
}