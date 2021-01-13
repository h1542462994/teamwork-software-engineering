package org.learning.server.model.complex

import org.learning.server.entity.OrgNode
import org.learning.server.entity.User

class Invitation {
    var id: Int = -1
    var inverse: Boolean = false
    var user: User = User()
    var orgNode: OrgNode = OrgNode()
    var root: OrgNode = OrgNode()
}