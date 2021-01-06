package org.learning.server.repository

import org.learning.server.entity.CourseTag
import org.springframework.data.repository.CrudRepository

interface CourseTagRepository : CrudRepository<CourseTag, Int> {

}