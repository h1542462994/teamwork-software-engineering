package org.learning.server.entity

import com.fasterxml.jackson.annotation.JsonIgnore
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

    /**
     * 类型，符合[org.learning.server.entity.enums.MediaType]
     */
    var type: Int = 0
    var data: String = ""
    @ManyToOne
    @JsonIgnore
    var course: Course = Course()
}