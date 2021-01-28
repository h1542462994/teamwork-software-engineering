package org.learning.server.service.impl

import org.learning.server.common.TimeStampHelper
import org.learning.server.entity.CourseOpen
import org.learning.server.entity.User
import org.learning.server.model.common.Response
import org.learning.server.model.common.Responses
import org.learning.server.model.complex.CourseOpenInfo
import org.learning.server.repository.CourseOpenRepository
import org.learning.server.service.ICourseOpenService
import org.learning.server.service.ICourseService
import org.learning.server.service.IOrgService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CourseOpenService : ICourseOpenService {
    @Autowired
    lateinit var courseOpenRepository: CourseOpenRepository
    @Autowired
    lateinit var orgService: IOrgService
    @Autowired
    lateinit var courseService: ICourseService
    override fun create(orgId: Int, courseId: Int, user: User): Response<CourseOpen> {
        val orgNode = orgService.getEntity(orgId)
        val course = courseService.getCourseEntity(courseId)
        orgService.guardAdmin(orgNode, user)
        val courseOpen = courseOpenRepository.save(
            CourseOpen().apply {
                this.orgNode = orgNode
                this.course = course
                this.isEdit = false
                this.startTime = TimeStampHelper.now()
                this.endTime = TimeStampHelper.now()
            }
        )
        return Responses.ok(courseOpen)
    }

    private fun getCourseOpenInfo(courseOpen: CourseOpen): CourseOpenInfo {
        return courseOpen.toCourseOpenInfo(orgService.getOrganizationOfNode(courseOpen.orgNode!!))
    }

    override fun list(user: User): Iterable<CourseOpenInfo> {
        // 查找所有的courseOpen
        // 首先，获得所有与之相关的节点信息
        val orgNodes = orgService.getOrgNodesOfUser(user)
        // 其次获取开课信息
        val courseOpens = orgNodes.flatMap { courseOpenRepository.findAllByOrgNode(it) }.distinctBy { it.id }
        return courseOpens
            .filter { !orgService.isAdmin(it.orgNode!!, user) }
            .map { getCourseOpenInfo(it) }
    }

    override fun listOfOrgNode(orgId: Int, user: User): Iterable<CourseOpenInfo> {
        val orgNode = orgService.getEntity(orgId)
        orgService.guardVisit(orgNode, user)
        val orgNodes = orgService.getFlatOrgNodesOfOrgNode(orgNode)
        return orgNodes.flatMap { it ->
            courseOpenRepository.findAllByOrgNode(it).map { it2 ->
                getCourseOpenInfo(it2)
            }
        }.distinctBy { it.id }
    }
}