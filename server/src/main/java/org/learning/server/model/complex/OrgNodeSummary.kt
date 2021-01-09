package org.learning.server.model.complex

import java.util.*

/**
 * 表示一个Department节点的概览数据
 */
open class OrgNodeSummary {
    open var id: Int = -1
    open var name: String = ""
    open var description: String = ""
    open var children: List<OrgNodeSummary> = LinkedList()
}