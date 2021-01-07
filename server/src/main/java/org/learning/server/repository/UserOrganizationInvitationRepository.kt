package org.learning.server.repository

import org.learning.server.entity.Organization
import org.learning.server.entity.UserOrganizationInvitation
import org.springframework.data.repository.CrudRepository

interface UserOrganizationInvitationRepository : CrudRepository<UserOrganizationInvitation, Int> {
    fun findAllByOrganizationAndInverse(organization: Organization, inverse: Boolean): List<UserOrganizationInvitation>
}