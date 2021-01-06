package org.learning.server.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import java.io.Serializable
import java.util.*
import javax.persistence.*

/**
 * 表示一个部门，一个部门必须隶属于一个组织
 */
@Entity
class Department : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = -1
    var name: String = ""
    var description: String = ""
    @JsonIgnore
    @ManyToOne(targetEntity = Organization::class)
    var organization: Organization = Organization()
    @OneToMany(targetEntity = UserDepartment::class, mappedBy = "department")
    var userDepartments: List<UserDepartment> = LinkedList()
}