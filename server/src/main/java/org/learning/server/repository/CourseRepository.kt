package org.learning.server.repository

import org.learning.server.entity.Course
import org.learning.server.entity.User
import org.springframework.data.repository.CrudRepository
import java.util.*

interface CourseRepository: CrudRepository<Course, Int> {
    fun findAllByOwner(owner: User): List<Course>
    fun findAllByAdminUsersContains(adminUser: User): List<Course>
}