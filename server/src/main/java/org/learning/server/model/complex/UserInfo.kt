package org.learning.server.model.complex

import org.learning.server.common.IMerge
import java.util.*

class UserInfo : IMerge<UserInfo> {
    var uid: String = ""
    var name: String = ""
    var email: String = ""
    var sex: Boolean = false
    var tags: HashSet<UserTag> = HashSet()
    override fun merge(other: IMerge<UserInfo>) {
        other as UserInfo
        if (uid != other.uid) {
            throw IllegalArgumentException("不能合并两个不同的user")
        }
        tags.addAll(other.tags)
    }

    override fun equals(other: Any?): Boolean {
        return if(other is UserInfo){
            this.uid == other.uid
        } else {
            false
        }
    }

    override fun hashCode(): Int {
        return uid.hashCode()
    }
}