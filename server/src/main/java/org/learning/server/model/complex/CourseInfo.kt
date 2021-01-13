package org.learning.server.model.complex

import com.fasterxml.jackson.annotation.JsonFormat
import org.learning.server.entity.CourseTag
import org.learning.server.entity.User
import java.sql.Timestamp
import java.util.*
import javax.persistence.ManyToMany
import javax.persistence.ManyToOne

@Deprecated("")
class CourseInfo {
    var id: Int = -1
    var pic: String? = null
    var name: String = ""
    var info: String = ""
    var inEdit: Boolean = true
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SS", timezone = "UTC")
    var createTime: Timestamp = Timestamp(0)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SS", timezone = "UTC")
    var editTime: Timestamp = Timestamp(0)
    var courseTags: MutableList<CourseTag> = LinkedList()
    var owner: User = User()
    /**
     * 是否对所有人公开（没有订阅的用户）
     */
    var isPublic: Boolean = false
    var adminUsers: MutableList<User> = LinkedList()
}