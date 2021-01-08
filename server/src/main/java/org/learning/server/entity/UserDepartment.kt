package org.learning.server.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema
import org.learning.server.entity.base.DepartmentBase
import org.learning.server.entity.base.UserExtendInfo
import javax.persistence.*

/**
 * 表示用户和部门之间的关系
 */
@Entity
@Deprecated("")
class UserDepartment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = -1
    @ManyToOne(targetEntity = User::class)
    var user: User = User()
    @ManyToOne(targetEntity = Department::class)
    @JsonIgnore
    var department: Department = Department()
    /**
     * 等级，level=0表示普通用户，level=1表示次管理员，level=2表示主管理员
     */
    var level: Int = 0

    // partition function
    @Deprecated("")
    fun toBase(): UserExtendInfo {
        return UserExtendInfo().apply {
            user = this@UserDepartment.user.toBase()
            level = this@UserDepartment.level
        }
    }
}