package org.learning.server.model.complex

class UserTag {
    var uid: String = ""
    var nodeId: Int = -1
    var name: String = ""
    var depth: Int = 0
    var level: Int = 0

    override fun equals(other: Any?): Boolean {
        return if(other is UserTag) {
            uid == other.uid && nodeId == other.nodeId
        } else {
            false
        }
    }

    override fun hashCode(): Int {
        var result = uid.hashCode()
        result = 31 * result + nodeId
        return result
    }

}
