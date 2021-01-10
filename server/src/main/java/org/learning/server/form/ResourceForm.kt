package org.learning.server.form

import org.learning.server.entity.Course
import org.learning.server.entity.Resource

class ResourceForm {
    var name: String = ""
    var type: Int = 0
    var data: String = ""

    fun toResource(course: Course): Resource {
        return Resource().apply {
            this.name = this@ResourceForm.name
            this.type = this@ResourceForm.type
            this.data = this@ResourceForm.data
            this.course = course
        }
    }
}