package org.learning.server.model.complex

import org.learning.server.entity.Course
import org.learning.server.entity.OrgNode
import java.sql.Timestamp

class CourseOpenInfo {
    var id: Int = -1
    var orgNode: OrgNode? = OrgNode()
    var root: OrgNode? = OrgNode()
    var course: Course = Course()
    var isEdit: Boolean = false
    var startTime: Timestamp? = null
    var endTime: Timestamp? = null
}