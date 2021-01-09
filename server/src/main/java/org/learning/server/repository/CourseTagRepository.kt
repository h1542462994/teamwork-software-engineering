package org.learning.server.repository

import org.learning.server.entity.CourseTag
import org.springframework.data.repository.CrudRepository
import java.util.*

interface CourseTagRepository : CrudRepository<CourseTag, Int> {
    fun findByName(name: String): Optional<CourseTag>
}