package org.learning.server.entity

import com.fasterxml.jackson.annotation.JsonIgnore
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
    var indexAt: Int = 0
    @ManyToOne
    var resource: Resource = Resource()

    @ManyToOne
    @JsonIgnore
    var chapter: Chapter = Chapter()
}