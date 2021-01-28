package org.learning.server.service

import org.learning.server.entity.CourseOpen
import org.learning.server.entity.User
import org.learning.server.model.common.Response
import org.learning.server.model.complex.CourseOpenInfo

interface ICourseOpenService {
    fun create(orgId: Int, courseId: Int, user: User): Response<CourseOpen>
    /**
     * 获取当前用户的学习列表
     */
    fun list(user: User): Iterable<CourseOpenInfo>
    fun listOfOrgNode(orgId: Int, user: User): Iterable<CourseOpenInfo>
}