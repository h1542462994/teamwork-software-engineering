package org.learning.server.repository

import org.learning.server.entity.Organization
import org.springframework.data.repository.CrudRepository

interface OrganizationRepository : CrudRepository<Organization, Int> {
}