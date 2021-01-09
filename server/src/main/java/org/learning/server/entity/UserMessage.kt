package org.learning.server.entity

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnore
import java.sql.Timestamp
import javax.persistence.*

/**
 * 用户的消息列表
 */
@Entity
class UserMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = -1
    @ManyToOne
    @JsonIgnore
    var user: User = User()
    /**
     * 消息类型
     */
    var type: String = ""
    /**
     * 消息实体
     */
    var message: String = ""
    /**
     * 消息的链接（可选）
     */
    var url: String? = null
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SS", timezone = "UTC")
    var createTime: Timestamp = Timestamp(0)
    var isRead: Boolean = false
}