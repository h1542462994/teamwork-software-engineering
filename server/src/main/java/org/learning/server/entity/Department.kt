package org.learning.server.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import java.io.Serializable
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
}