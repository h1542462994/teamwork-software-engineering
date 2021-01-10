package org.learning.server.repository

import org.learning.server.entity.Chapter
import org.learning.server.entity.Course
import org.springframework.data.repository.CrudRepository

interface ChapterRepository: CrudRepository<Chapter, Int> {
    fun findAllByCourseOrderByIndexAt(course: Course): List<Chapter>
}