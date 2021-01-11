package org.learning.server.repository

import org.learning.server.entity.Course
import org.springframework.data.repository.CrudRepository
import java.util.*

interface CourseRepository: CrudRepository<Course, Integer> {
    fun findAllBy():List<Course>
    fun findById(id:Int?):Optional<Course>
}