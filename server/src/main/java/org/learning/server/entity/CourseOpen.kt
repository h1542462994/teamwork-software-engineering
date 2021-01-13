package org.learning.server.entity

import org.learning.server.model.complex.CourseOpenInfo
import java.sql.Timestamp
import javax.persistence.*

/**
 * 组织中开设的课程，或者用户自己学习的课程（当orgNode为空时）
 */
@Entity
class CourseOpen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = -1
    @ManyToOne
    var orgNode: OrgNode? = OrgNode()
    @ManyToOne
    var course: Course = Course()
    var isEdit: Boolean = false
    var startTime: Timestamp? = null
    var endTime: Timestamp? = null

    fun toCourseOpenInfo(root: OrgNode): CourseOpenInfo {
        return CourseOpenInfo().apply {
            this.id = this@CourseOpen.id
            this.orgNode = this@CourseOpen.orgNode
            this.course = this@CourseOpen.course
            this.isEdit = this@CourseOpen.isEdit
            this.startTime = this@CourseOpen.startTime
            this.endTime = this@CourseOpen.endTime
            this.root = root
        }
    }
}