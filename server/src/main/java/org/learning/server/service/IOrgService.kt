package org.learning.server.service

import org.learning.server.entity.Organization

interface IOrgService {
    fun findAll(): Iterable<Organization>
}