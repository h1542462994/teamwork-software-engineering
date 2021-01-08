package org.learning.server.entity

import org.learning.server.model.complex.OrgNodeSummary
import org.learning.server.model.complex.OrgSummary
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

    // partition function
    /**
     * 转换成OrgSummary中的部分数据
     */
    fun toOrgSummaryPart(): OrgSummary {
        if (parentId != null){
            throw IllegalArgumentException("只有根节点/组织才能使用该函数")
        }
        return OrgSummary().apply {
            id = this@OrgNode.id
            name = this@OrgNode.name
            description = this@OrgNode.description
            public = this@OrgNode.public
        }
    }

    fun toOrgNodeSummaryPart(): OrgNodeSummary {
        if (parentId == null){
            throw IllegalArgumentException("只有非根节点/部门节点才能使用该函数")
        }
        return OrgNodeSummary().apply {
            id = this@OrgNode.id
            name = this@OrgNode.name
            description = this@OrgNode.description
        }
    }
}