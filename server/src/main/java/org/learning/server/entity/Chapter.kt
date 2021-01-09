package org.learning.server.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import org.learning.server.entity.base.ChapterInfo
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
    var indexAt: Int = 0
    @ManyToOne
    @JsonIgnore
    var course: Course = Course()

    fun toChapterInfo(): ChapterInfo {
        return ChapterInfo().apply {
            this.id = this@Chapter.id
            this.name = this@Chapter.name
        }
    }
}