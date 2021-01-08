package org.learning.server.entity

import javax.persistence.*

@Entity
class CourseCommentArea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = -1
    @OneToOne
    var courseOpen: CourseOpen = CourseOpen()
    @ManyToOne
    var chapter: Chapter = Chapter()

}