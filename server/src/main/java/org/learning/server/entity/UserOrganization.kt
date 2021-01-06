package org.learning.server.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import org.learning.server.entity.base.UserExtendInfo
import javax.persistence.*

@Entity
class UserOrganization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = -1
    @ManyToOne(targetEntity = User::class)
    var user: User = User()
    @ManyToOne(targetEntity = Organization::class)
    @JsonIgnore
    var organization: Organization = Organization()
    /**
     * 等级，level=0表示普通用户，level=1表示次管理员，level=2表示主管理员
     */
    var level: Int = 0

    @Deprecated("")
    fun toBase(): UserExtendInfo {
        return UserExtendInfo().apply {
            user = this@UserOrganization.user.toBase()
            level = this@UserOrganization.level
        }
    }
}