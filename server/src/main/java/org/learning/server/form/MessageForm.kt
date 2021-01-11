package org.learning.server.form

import org.learning.server.common.TimeStampHelper
import org.learning.server.entity.User
import org.learning.server.entity.UserMessage
import java.sql.Timestamp
import java.time.Clock
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset

class MessageForm {
    /**
     * 消息类型，为[org.learning.server.entity.enums.MessageType]中的值
     */
    var type: String = ""
    var message: String = ""
    var url: String? = null

    fun toUserMessage(user: User): UserMessage {
        return UserMessage().apply {
            this.user = user
            this.type = this@MessageForm.type
            this.message = this@MessageForm.message
            this.url = this@MessageForm.url
            this.createTime = TimeStampHelper.now()
            this.isRead = false
        }
    }
}