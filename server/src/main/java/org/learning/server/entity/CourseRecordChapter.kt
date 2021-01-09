package org.learning.server.entity

import java.util.*
import javax.persistence.*

@Entity
class CourseRecordChapter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = -1
    @ManyToOne
    var chapter: Chapter = Chapter()
    var tabIndex: Int = -1
    @OneToMany
    var courseRecordMedias: List<CourseRecordMedia> = LinkedList()
}