package org.learning.server.service

import org.learning.server.entity.Course
import org.learning.server.form.CourseForm
import org.learning.server.model.common.Response

interface ICourseService {
    fun all(): Iterable<Course>
    fun create(courseForm: CourseForm): Response<Course>
    fun delete(id: Int): Boolean
}