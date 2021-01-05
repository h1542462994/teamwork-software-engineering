package org.learning.server.entity

import java.io.Serializable
import java.util.*
import javax.persistence.*

/**
 * 表示一个组织，一个组织下有若干的部门。
 */
@Entity
class Organization: Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = -1
    var name: String = ""
    var description: String = ""
    @OneToMany(targetEntity = Department::class, mappedBy = "organization")
    var departments: List<Department> = LinkedList()
}