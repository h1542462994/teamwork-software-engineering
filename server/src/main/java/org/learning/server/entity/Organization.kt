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
@Deprecated("")
class Organization:  Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = -1
    var name: String = ""
    var description: String = ""
    @OneToMany(targetEntity = Department::class, mappedBy = "organization")
    var departments: MutableCollection<Department> = LinkedList()
    @OneToMany(targetEntity = UserOrganization::class, mappedBy = "organization")
    @JsonIgnore
    var userOrganizations: MutableCollection<UserOrganization> = LinkedList()
    @OneToMany(targetEntity = UserOrganizationInvitation::class, mappedBy = "organization")
    @JsonIgnore
    var userOrganizationInvitations: MutableCollection<UserOrganizationInvitation> = LinkedList()

    // calculated property
    val users get() = userOrganizations.map { it.user.toBase() }
            // plus 各个部门的user，这里使用到了高阶函数
        .plus(userOrganizations.flatMap { it.organization.departments }
            .flatMap { it.users }).distinct()
    val owner get() = userOrganizations.find { it.level == 2 }?.user?.toBase()
    // partition function
    fun toBase(): OrganizationBase {
        return OrganizationBase().apply {
            id = this@Organization.id
            name = this@Organization.name
            description = this@Organization.description
        }
    }

    /**
     * 获取结构信息（包括是否处于邀请状态）
     */
    fun toStructInfo(user: User): OrganizationBase {
        return OrganizationBase().apply {
            id = this@Organization.id
            name = this@Organization.name
            description = this@Organization.description
            owner = this@Organization.owner
            departments = this@Organization.departments.map { it.toBase() }
            state = this@Organization.userOrganizationInvitations.find { it.user.uid == user.uid && it.active }?.state
        }
    }
}