package org.learning.server.service.impl

import org.learning.server.entity.Organization
import org.learning.server.repository.OrganizationRepository
import org.learning.server.service.IOrgService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class OrgService : IOrgService {
    @Autowired
    lateinit var organizationRepository: OrganizationRepository

    override fun findAll(): Iterable<Organization> {
        return organizationRepository.findAll()
    }

    override fun findById(id: Int): Optional<Organization> {
        return organizationRepository.findById(id)
    }
}