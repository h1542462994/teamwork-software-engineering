package org.learning.server.entity.base

import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

open class DepartmentBase {
    open var id: Int = -1
    open var name: String = ""
    open var description: String = ""
}