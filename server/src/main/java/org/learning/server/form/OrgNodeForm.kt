package org.learning.server.form

import com.sun.istack.NotNull
import org.learning.server.entity.OrgNode
import org.learning.server.entity.User
import javax.validation.constraints.Size

class OrgNodeForm {
    @NotNull
    @Size(max = 100)
    var name: String = ""
    @NotNull
    @Size(max = 200)
    var description: String = ""
    var public: Boolean = false
    var parentId: Int? = null

    fun toOrgNode(): OrgNode {
        return OrgNode().apply {
            this.name = this@OrgNodeForm.name
            this.description = this@OrgNodeForm.description
            this.public = this@OrgNodeForm.public
            this.parentId = this@OrgNodeForm.parentId
        }
    }
}