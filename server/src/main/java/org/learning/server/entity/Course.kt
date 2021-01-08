package org.learning.server.entity

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
    var createTime: Timestamp = Timestamp(0)
    var editTime: Timestamp = Timestamp(0)
    @ManyToMany(targetEntity = CourseTag::class)
    var courseTags: List<CourseTag> = LinkedList()
    @ManyToOne
    var owner: User = User()
    @ManyToMany
    var adminUsers: List<User> = LinkedList()
}