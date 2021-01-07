package org.learning.server.model.complex

import org.learning.server.entity.base.OrganizationBase
import java.util.*

@Computed
class OrganizationGrouped {
    var ofIn: Iterable<OrganizationBase> = LinkedList()
    var ofOthers: Iterable<OrganizationBase> = LinkedList()
}