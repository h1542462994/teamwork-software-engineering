package org.learning.server.entity

import javax.persistence.*

@Entity
class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = -1
    @ManyToOne
    var user: User = User()
    var message: String = ""
    @ManyToOne
    var userTo: User? = null
    @ManyToOne
    var courseCommentArea: CourseCommentArea = CourseCommentArea()
}