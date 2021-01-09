package org.learning.server.entity.base

import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Deprecated("")
class DepartmentBase {
    var id: Int = -1
    var name: String = ""
    var description: String = ""
    var organization: OrganizationBase = OrganizationBase()
    var level: Int = 0
}