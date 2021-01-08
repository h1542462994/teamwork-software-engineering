package org.learning.server.entity

import java.sql.Timestamp
import javax.persistence.ManyToOne

/**
 * 用户的消息列表
 */
class UserMessage {
    var id: Int = -1
    @ManyToOne
    var user: User = User()
    var type: String = ""
    var message: String = ""
    var createTime: Timestamp = Timestamp(0)
    var isRead: Boolean = false
}