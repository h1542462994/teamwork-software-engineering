package org.learning.server.entity

import java.util.*
import javax.persistence.*

/**
 * 课程进度记录的总结点
 */
@Entity
class CourseRecordArea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = -1
    @ManyToOne
    var courseOpen: CourseOpen = CourseOpen()
    @ManyToOne
    var user: User = User()
    @OneToMany
    var courseRecordChapters: List<CourseRecordChapter> = LinkedList()
}