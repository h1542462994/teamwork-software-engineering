package org.learning.server.service.impl

import org.learning.server.common.SessionHelper
import org.learning.server.entity.Organization
import org.learning.server.entity.User
import org.learning.server.model.complex.OrganizationGrouped
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

    override fun grouped(user: User): OrganizationGrouped {
        val all = findAll()
        val orgsIn = all.filter { it -> it.users.find { it.uid == user.uid } != null }
        return OrganizationGrouped().apply {
            ofIn = orgsIn
            ofOthers = all.subtract(ofIn)
        }
    }
}