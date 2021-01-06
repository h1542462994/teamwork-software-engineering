package org.learning.server.model.complex

import org.learning.server.entity.Organization
import java.util.*

@Computed
class OrganizationGrouped {
    var ofIn: Iterable<Organization> = LinkedList()
    var ofOthers: Iterable<Organization> = LinkedList()
}