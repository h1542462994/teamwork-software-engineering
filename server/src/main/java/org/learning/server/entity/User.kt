package org.learning.server.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import org.learning.server.entity.base.UserBase
import java.io.Serializable
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToMany

/**
 * a data of user
 */
@Entity
class User : Serializable {
    @Id
    var uid: String = ""
    var name: String = ""
    @JsonIgnore
    var password: String = ""
    var age: Int = 1
    var sex: Boolean = false
    var email: String = ""

    @OneToMany(targetEntity = UserDepartment::class, mappedBy = "user")
    @JsonIgnore
    var userDepartments: List<UserDepartment> = LinkedList()
    @OneToMany(targetEntity = UserOrganization::class, mappedBy = "user")
    @JsonIgnore
    var userOrganizations: List<UserOrganization> = LinkedList()
    @OneToMany(targetEntity = UserOrganizationInvitation::class, mappedBy = "user")
    @JsonIgnore
    var userOrganizationInvitations: List<UserOrganizationInvitation> = LinkedList()

    // calculated property
    private val departments get() = userDepartments.map { it.department.toBase().apply { level = it.level } }
    private val organizations get() = userOrganizations.map { it.organization.toBase().apply { level = it.level } }

    // partition function
    fun toBase(): UserBase {
        return UserBase().apply {
            uid = this@User.uid
            name = this@User.name
            age = this@User.age
            sex = this@User.sex
            email = this@User.email
            departments = this@User.departments
            organizations = this@User.organizations
        }
    }

    @Deprecated("")
    fun toBasePart(): UserBase {
        return UserBase().apply {
            uid = this@User.uid
            name = this@User.name
            age = this@User.age
            sex = this@User.sex
            email = this@User.email
        }
    }
}