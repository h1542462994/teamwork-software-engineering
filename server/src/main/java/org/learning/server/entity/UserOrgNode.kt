package org.learning.server.entity

import org.learning.server.model.complex.UserInfo
import org.learning.server.model.complex.UserTag
import java.util.*
import javax.persistence.*
import kotlin.collections.HashSet

/**
 * 表示用户和一个架构节点的所属关系
 */
@Entity
class UserOrgNode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null
    @ManyToOne
    var user: User = User()
    @ManyToOne
    var orgNode: OrgNode = OrgNode()
    /**
     * 用户级别，0普通，1管理员，2主管理员（一个组织只有一个根级主管理员）
     */
    var level: Int = 0

    fun toUserInfo(depth: Int): UserInfo {
        return UserInfo().apply {
            uid = user.uid
            name = user.name
            email = user.email
            sex = user.sex
            this.tags = HashSet(listOf(UserTag().apply {
                this.uid = this@UserOrgNode.user.uid
                this.depth = depth
                this.level = this@UserOrgNode.level
                this.nodeId = this@UserOrgNode.id!!
            }))
        }
    }
}