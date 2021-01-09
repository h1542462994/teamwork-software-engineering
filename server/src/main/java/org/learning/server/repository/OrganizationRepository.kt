package org.learning.server.repository

import org.learning.server.entity.Organization
import org.springframework.data.repository.CrudRepository

@Deprecated("")
interface OrganizationRepository : CrudRepository<Organization, Int> {

}