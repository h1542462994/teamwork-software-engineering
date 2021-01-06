package org.learning.server.service

import org.learning.server.entity.Organization
import java.util.*

interface IOrgService {
    fun findAll(): Iterable<Organization>
    fun findById(id: Int): Optional<Organization>
}