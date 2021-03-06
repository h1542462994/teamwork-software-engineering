package org.learning.server.repository

import org.learning.server.entity.OrgNode
import org.learning.server.entity.User
import org.learning.server.entity.UserOrgNode
import org.springframework.data.repository.CrudRepository
import java.util.*

interface UserOrgNodeRepository: CrudRepository<UserOrgNode, Int> {
    fun findAllByOrgNodeAndLevel(orgNode: OrgNode, level: Int): List<UserOrgNode>
    fun findAllByOrgNode(orgNode: OrgNode): List<UserOrgNode>
    fun findAllByUser(user: User): List<UserOrgNode>
    fun findByUserAndOrgNode(user: User, orgNode: OrgNode): Optional<UserOrgNode>
}