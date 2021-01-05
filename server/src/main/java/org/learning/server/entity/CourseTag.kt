package org.learning.server.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import java.util.*
import javax.persistence.*

@Entity
class CourseTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = -1
    var name: String = ""
    @JsonIgnore
    @ManyToMany(targetEntity = Course::class, mappedBy = "courseTags")
    var courses: List<Course> = LinkedList()
}
