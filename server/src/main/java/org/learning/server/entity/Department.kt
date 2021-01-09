package org.learning.server.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import org.learning.server.entity.base.DepartmentBase
import java.io.Serializable
import java.util.*
import javax.persistence.*

/**
 * 表示一个部门，一个部门必须隶属于一个组织
 */
@Entity
@Deprecated("")
class Department :  Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = -1
    var name: String = ""
    var description: String = ""

    @JsonIgnore
    @ManyToOne(targetEntity = Organization::class)
    var organization: Organization = Organization()
    @OneToMany(targetEntity = UserDepartment::class, mappedBy = "department")
    @JsonIgnore
    var userDepartments: List<UserDepartment> = LinkedList()
    // calculated property
    val users get() = userDepartments.map { it.user.toBase() }.distinct()
    // partition function
    fun toBase() : DepartmentBase {
        return DepartmentBase().apply {
            id = this@Department.id
            name = this@Department.name
            description = this@Department.description
            organization = this@Department.organization.toBase()
        }
    }
    @Deprecated("")
    fun toBasePart(): DepartmentBase {
        return DepartmentBase().apply {
            id = this@Department.id
            name = this@Department.name
            description = this@Department.description
        }
    }
}