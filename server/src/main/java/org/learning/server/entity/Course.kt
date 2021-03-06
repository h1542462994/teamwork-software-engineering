package org.learning.server.entity

import com.fasterxml.jackson.annotation.JsonFormat
import org.learning.server.common.TimeStampHelper
import org.learning.server.form.CourseForm
import org.learning.server.model.complex.CourseInfo
import java.sql.Timestamp
import java.util.*
import javax.persistence.*

@Entity
class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = -1
    var pic: String? = null
    var name: String = ""
    var info: String = ""
    var inEdit: Boolean = true
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SS", timezone = "UTC")
    var createTime: Timestamp = Timestamp(0)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SS", timezone = "UTC")
    var editTime: Timestamp = Timestamp(0)
    @ManyToMany(targetEntity = CourseTag::class)
    var courseTags: MutableList<CourseTag> = LinkedList()
    @ManyToOne
    var owner: User = User()
    /**
     * 是否对所有人公开（没有订阅的用户）
     */
    var isPublic: Boolean = false
    @ManyToMany
    var adminUsers: MutableList<User> = LinkedList()

    fun updateFrom(courseForm: CourseForm) {
        name = courseForm.name
        info = courseForm.info
        pic = courseForm.pic
        isPublic = courseForm.public
        editTime = TimeStampHelper.now()
    }

    @Deprecated("")
    fun toCourseInfo(): CourseInfo {
        return CourseInfo().apply {
            this.id = this@Course.id
            this.pic = this@Course.pic
            this.name = this@Course.name
            this.info = this@Course.info
            this.inEdit = this@Course.inEdit
            this.isPublic = this@Course.isPublic
            this.createTime = this@Course.createTime
            this.editTime = this@Course.editTime
            this.courseTags = this@Course.courseTags
            this.owner = this@Course.owner
            this.adminUsers = this@Course.adminUsers
        }
    }
}