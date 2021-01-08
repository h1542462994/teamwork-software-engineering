package org.learning.server.entity

import javax.persistence.*

/**
 * 课程的一个章节
 */
@Entity
class Chapter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = -1
    var name: String = ""
    /**
     * 显示时的位置
     */
    var order: Int = 0
    @ManyToOne
    var course: Course = Course()
}