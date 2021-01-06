package org.learning.server.entity.base

import java.util.*

class UserBase {
    var uid: String = ""
    var name: String = ""
    var age: Int = 1
    var sex: Boolean = false
    var email: String = ""
    var departments: List<DepartmentBase> = LinkedList()
    var organizations: List<OrganizationBase> = LinkedList()

    override fun equals(other: Any?): Boolean {
        return if (other is UserBase) {
            Objects.equals(uid, other.uid)
        } else {
            false;
        }
    }

    override fun hashCode(): Int {
        return uid.hashCode()
    }
}