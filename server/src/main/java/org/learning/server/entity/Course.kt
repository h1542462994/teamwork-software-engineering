package org.learning.server.entity

import java.util.*
import javax.persistence.*

@Entity
class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = -1
    var name: String = ""
    var info: String = ""
    @ManyToMany(targetEntity = CourseTag::class)
    var courseTags: List<CourseTag> = LinkedList()
}