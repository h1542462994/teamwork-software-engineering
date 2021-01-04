package org.learning.server.entity

import javax.persistence.*

/**
 * 表示一个部门，一个部门必须隶属于一个组织
 */
@Entity
class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = -1
    var name: String = ""
    var description: String = ""
    @ManyToOne(targetEntity = Organization::class)
    var organization: Organization = Organization()
}