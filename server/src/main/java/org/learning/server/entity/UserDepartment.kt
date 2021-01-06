package org.learning.server.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

/**
 * 表示用户和部门之间的关系
 */
@Entity
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
}