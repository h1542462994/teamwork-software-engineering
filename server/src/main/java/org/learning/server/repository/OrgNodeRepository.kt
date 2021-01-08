package org.learning.server.repository

import org.learning.server.entity.OrgNode
import org.springframework.data.repository.CrudRepository

interface OrgNodeRepository : CrudRepository<OrgNode, Int> {
    fun findAllByParentId(parentId: Int?): List<OrgNode>
}