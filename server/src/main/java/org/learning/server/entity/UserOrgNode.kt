package org.learning.server.entity

import javax.persistence.*

/**
 * 表示用户和一个架构节点的所属关系
 */
@Entity
class UserOrgNode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = -1
    @ManyToOne
    var user: User = User()
    @ManyToOne
    var orgNode: OrgNode = OrgNode()
    /**
     * 用户级别，0普通，1管理员，2主管理员（一个组织只有一个根级主管理员）
     */
    var level: Int = 0
}