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
class User : UserBase(), Serializable {
    @Id
    override var uid: String = ""
    override var name: String = ""

    @JsonIgnore
    override var password: String = ""
    override var age: Int = 1
    override var sex: Boolean = false
    override var email: String = ""

    @OneToMany(targetEntity = UserDepartment::class, mappedBy = "user")
    @JsonIgnore
    var userDepartments: List<UserDepartment> = LinkedList()

    @OneToMany(targetEntity = UserOrganization::class, mappedBy = "user")
    @JsonIgnore
    var userOrganizations: List<UserOrganization> = LinkedList()

    // calculated property
    val departments get() = userDepartments.map { it.department.toBase() }
    val organizations get() = userOrganizations.map { it.organization.toBase() }

    // partition function
    fun toBase(): UserBase {
        return UserBase().apply {
            uid = this@User.uid
            name = this@User.name
            age = this@User.age
            sex = this@User.sex
            email = this@User.email
        }
    }
}