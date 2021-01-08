package org.learning.server.entity

import javax.persistence.*

/**
 * 表示一个架构节点，当节点没有parent时，表示其为组织（organization），否则是一个部门（department），为树型的架构
 */
@Entity
class OrgNode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = -1
    var name: String = ""
    var description: String = ""
//    @ManyToOne
//    var parent: OrgNode = OrgNode()
    /**
     * 父级节点，这里需要手动管理。
     */
    var parentId: Int? = null
    /**
     * 表示这个结构节点是否公开（仅对顶级节点有效）
     */
    var public: Boolean = false
}