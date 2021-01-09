package org.learning.server.entity

import javax.persistence.*

/**
 * 一个课程的资料库
 */
@Entity
class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = -1
    var name: String = ""
    var type: Int = 0
    var data: String = ""
    @ManyToOne
    var course: Course = Course()
}