package org.learning.server.repository

import org.learning.server.entity.CourseOpen
import org.learning.server.entity.OrgNode
import org.springframework.data.repository.CrudRepository

interface CourseOpenRepository : CrudRepository<CourseOpen, Int> {
    fun findAllByOrgNode(orgNode: OrgNode): List<CourseOpen>
}