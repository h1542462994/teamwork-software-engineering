package org.learning.server.entity.base

import org.learning.server.entity.User
import org.learning.server.model.Department
import java.util.*

class OrganizationBase {
    var id: Int = -1
    var name: String = ""
    var description: String = ""
    var level: Int = 0
    var departments: List<DepartmentBase> = LinkedList()

    var owner: UserBase? = UserBase()
    var state: Int? = null

    override fun equals(other: Any?): Boolean {
        return if (other is OrganizationBase) {
            id == other.id
        } else {
            false
        }
    }

    override fun hashCode(): Int {
        return id
    }
}