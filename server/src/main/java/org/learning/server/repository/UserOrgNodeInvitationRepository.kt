package org.learning.server.repository

import org.learning.server.entity.OrgNode
import org.learning.server.entity.User
import org.learning.server.entity.UserOrgNode
import org.learning.server.entity.UserOrgNodeInvitation
import org.springframework.data.repository.CrudRepository
import java.util.*

interface UserOrgNodeInvitationRepository: CrudRepository<UserOrgNodeInvitation, Int> {
    fun findAllByOrgNode(orgNode: OrgNode): List<UserOrgNodeInvitation>
    fun findByUserAndOrgNodeAndInverse(user: User, orgNode: OrgNode, inverse: Boolean): Optional<UserOrgNodeInvitation>
}