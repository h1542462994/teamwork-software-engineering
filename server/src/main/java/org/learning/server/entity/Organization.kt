package org.learning.server.entity

import java.util.*
import javax.persistence.*

/**
 * 表示一个组织，一个组织下有若干的部门。
 */
@Entity
class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = -1
    var name: String = ""
    var description: String = ""
    @OneToMany(targetEntity = Department::class, mappedBy = "organization")
    var departments: List<Department> = LinkedList()
}