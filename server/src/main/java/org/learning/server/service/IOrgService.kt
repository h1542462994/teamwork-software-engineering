package org.learning.server.service

import org.learning.server.entity.Organization
import org.learning.server.entity.User
import org.learning.server.model.complex.OrganizationGrouped
import java.util.*

interface IOrgService {
    fun findAll(): Iterable<Organization>
    fun findById(id: Int): Optional<Organization>
    fun grouped(user: User): OrganizationGrouped
}