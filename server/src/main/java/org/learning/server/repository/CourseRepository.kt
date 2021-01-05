package org.learning.server.repository

import org.learning.server.entity.Course
import org.springframework.data.repository.CrudRepository

interface CourseRepository : CrudRepository<Course, Int> {

}