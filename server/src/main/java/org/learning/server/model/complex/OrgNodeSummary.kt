package org.learning.server.model.complex

import org.learning.server.common.IMerge
import java.util.*
import kotlin.collections.HashSet

/**
 * 表示一个Department节点的概览数据
 */
open class OrgNodeSummary : IMerge<OrgNodeSummary> {
    open var id: Int = -1
    open var name: String = ""
    open var description: String = ""
    open var children: HashSet<OrgNodeSummary> = HashSet()
    /**
     * 用户管理的级别，[org.learning.server.entity.enums.Level]
     */
    open var level: Int = 0

    override fun equals(other: Any?): Boolean {
        return if(other is OrgNodeSummary){
            this.id == other.id
        } else {
            false
        }
    }

    override fun hashCode(): Int {
        return id
    }


    override fun merge(other: IMerge<OrgNodeSummary>) {
        if (this != other) {
            throw IllegalArgumentException("不能合并两个不相等的节点")
        }
        for(child in (other as OrgNodeSummary).children){
            val meChild = this.children.find { it.id == child.id }
            if (meChild != null){
                // 如果存在相同节点则执行子节点的merge操作
                meChild.merge(child)
            } else {
                // 存在不同节点，则加入此节点
                this.children.add(child)
            }
        }
    }
}