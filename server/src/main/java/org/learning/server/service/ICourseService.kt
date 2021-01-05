package org.learning.server.service

import org.learning.server.entity.Course

interface ICourseService {
    fun findAll(): Iterable<Course>
}