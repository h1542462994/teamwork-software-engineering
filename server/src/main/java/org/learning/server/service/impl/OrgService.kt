package org.learning.server.service.impl

import org.learning.server.entity.Organization
import org.learning.server.entity.User
import org.learning.server.entity.UserOrganizationInvitation
import org.learning.server.entity.base.OrganizationBase
import org.learning.server.model.common.Response
import org.learning.server.model.common.Responses
import org.learning.server.model.complex.OrganizationGrouped
import org.learning.server.repository.OrganizationRepository
import org.learning.server.repository.UserOrganizationInvitationRepository
import org.learning.server.service.IOrgService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class OrgService : IOrgService {
    @Autowired
    lateinit var organizationRepository: OrganizationRepository
    @Autowired
    lateinit var userOrganizationInvitationRepository: UserOrganizationInvitationRepository

    override fun findAll(): Iterable<Organization> {
        return organizationRepository.findAll()
    }

    override fun findById(id: Int): Optional<Organization> {
        return organizationRepository.findById(id)
    }

    override fun grouped(user: User): OrganizationGrouped {
        val all = findAll()
        val orgsIn = all.filter { it -> it.users.find { it.uid == user.uid } != null }
        return OrganizationGrouped().apply {
            ofIn = orgsIn.map { it.toStructInfo(user) }
            ofOthers = all.map { it.toStructInfo(user) }.subtract(ofIn)
        }
    }

    override fun userInviteOrganization(orgId: Int, user: User): Response<OrganizationBase> {
        val organization = organizationRepository.findById(orgId)
        if (organization.isEmpty){
            return Responses.fail("该组织不存在")
        }
        val org = organization.get().toStructInfo(user)
        if (org.state == null) {
            // 如果没有对应的申请则添加申请
                // TODO 修改
//            organization.get().userOrganizationInvitations.add(
//                UserOrganizationInvitation().apply {
//                    this.user = user
//                    this.organization = organization.get()
//                    this.state = 0
//                    this.inverse = true
//                }
//            )
            userOrganizationInvitationRepository.save(
                UserOrganizationInvitation().apply {
                    this.user = user
                    this.organization = organization.get()
                    this.state = 0
                    this.inverse = true
                }
            )
            return Responses.ok(organization.get().toStructInfo(user).apply {
                state = 0
            })
        } else {
            // 获取处于活跃(0|1)的申请
            val invitation = organization.get().userOrganizationInvitations.find {
                it.user.uid == user.uid && (it.active)
            }!!
            // 表示你已经加入了这个部门
            if (invitation.state != 0) {
                return Responses.fail("你已经在这个部门内了")
            } else {
                userOrganizationInvitationRepository.delete(invitation)
            }
            return Responses.ok(organization.get().toStructInfo(user).apply {
                state = null
            })
        }
        // 保存提交


    }
}