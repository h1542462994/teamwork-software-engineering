package org.learning.server.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import org.learning.server.entity.base.OrganizationBase
import java.io.Serializable
import java.util.*
import javax.persistence.*

/**
 * 表示一个组织，一个组织下有若干的部门。
 */
@Entity
class Organization:  Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = -1
    var name: String = ""
    var description: String = ""
    @OneToMany(targetEntity = Department::class, mappedBy = "organization")
    var departments: List<Department> = LinkedList()
    @OneToMany(targetEntity = UserOrganization::class, mappedBy = "organization")
    @JsonIgnore
    var userOrganizations: List<UserOrganization> = LinkedList()
    // calculated property
    val users get() = userOrganizations.map { it.user.toBase() }
            // plus 各个部门的user，这里使用到了高阶函数
        .plus(userOrganizations.flatMap { it.organization.departments }
            .flatMap { it.users }).distinct()
    // partition function
    fun toBase(): OrganizationBase {
        return OrganizationBase().apply {
            id = this@Organization.id
            name = this@Organization.name
            description = this@Organization.description
        }
    }
}