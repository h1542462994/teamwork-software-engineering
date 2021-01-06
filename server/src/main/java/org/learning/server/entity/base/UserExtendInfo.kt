package org.learning.server.entity.base

@Deprecated("level 已经嵌入到DepartmentBase 和 OrganizationBase中")
class UserExtendInfo {
    var user: UserBase = UserBase()
    var level: Int = -1
}