package org.learning.server.repository

import org.learning.server.entity.Course
import org.learning.server.entity.Resource
import org.springframework.data.repository.CrudRepository

interface ResourceRepository : CrudRepository<Resource, Int> {
    fun findAllByCourse(course: Course): List<Resource>
}