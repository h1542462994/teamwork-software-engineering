package org.learning.server.entity

import java.util.*
import javax.persistence.*

/**
 * 课程一节中的一个tab
 */
@Entity
class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = -1
    var name: String = ""
    /**
     * 显示的位置
     */
    var order: Int = 0
    @ManyToMany
    var resource: List<Resource> = LinkedList()
}