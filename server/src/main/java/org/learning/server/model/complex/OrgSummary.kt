package org.learning.server.model.complex

import org.learning.server.entity.User

/**
 * 表示一个Organization的概览数据，供前端或者其他代码使用
 */
class OrgSummary : OrgNodeSummary() {
    var public: Boolean = false
    var owner: User = User()

    /**
     * 用户管理的级别，[org.learning.server.entity.enums.Level]
     */
    var level: Int = 0
}