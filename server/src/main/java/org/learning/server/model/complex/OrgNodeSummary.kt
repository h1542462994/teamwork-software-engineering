package org.learning.server.model.complex

import java.util.*
import kotlin.collections.HashSet

/**
 * 表示一个Department节点的概览数据
 */
open class OrgNodeSummary {
    open var id: Int = -1
    open var name: String = ""
    open var description: String = ""
    open var children: HashSet<OrgNodeSummary> = HashSet()

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
}