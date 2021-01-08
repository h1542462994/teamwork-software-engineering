package org.learning.server.service.impl

import org.learning.server.entity.*
import org.learning.server.entity.base.OrganizationBase
import org.learning.server.entity.base.UserBase
import org.learning.server.entity.enums.Level
import org.learning.server.exception.NoAllowedException
import org.learning.server.form.OrgNodeForm
import org.learning.server.model.common.Response
import org.learning.server.model.common.Responses
import org.learning.server.model.complex.OrgSummary
import org.learning.server.model.complex.OrganizationGrouped
import org.learning.server.repository.OrgNodeRepository
import org.learning.server.repository.OrganizationRepository
import org.learning.server.repository.UserOrgNodeRepository
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
    @Autowired
    lateinit var orgNodeRepository: OrgNodeRepository
    @Autowired
    lateinit var userOrgNodeRepository: UserOrgNodeRepository

    /**
     * 根据一个节点查找其组织结构的根节点
     */
    private fun getOrganizationOfNode(orgNode: OrgNode): OrgNode {
        var current = orgNode
        while (current.parentId != null) {
            current = orgNodeRepository.findById(current.parentId!!).get()
        }

        return current
    }

    /**
     * 查找组织（根节点）对应的概览数据
     * 包括组织的基础数据，一级部门的基础数据，组织主管理员。
     */
    private fun getOrganizationBase(orgNode: OrgNode): OrgSummary {
        if (orgNode.parentId != null) {
            throw IllegalArgumentException("orgNode必须是根节点");
        }
        return orgNode.toOrgSummaryPart().apply {
            owner = userOrgNodeRepository.findAllByOrgNodeAndLevel(orgNode, Level.MAINADMIN).first().user
            children = orgNodeRepository.findAllByParentId(orgNode.id).map { it.toOrgNodeSummaryPart() }
        }
    }

    private fun guardMainAdmin(orgNode: OrgNode, user: User) {
        // 向上查找组织节点
        val org = this.getOrganizationBase(this.getOrganizationOfNode(orgNode))
        if (org.owner.uid != user.uid) {
            throw NoAllowedException("你不是组织主管理员，无法进行此操作")
        }
    }

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

    override fun getInvitesById(orgId: Int): List<UserBase> {
        val organization = organizationRepository.findById(orgId).get()
        return userOrganizationInvitationRepository.findAllByOrganizationAndInverse(organization, true).map { it.toUserBase() }.distinct()
    }

    /**
     * 创建组织节点
     */
    override fun createOrganization(orgNodeForm: OrgNodeForm, user: User): Response<OrgNode> {
        var orgNode: OrgNode = orgNodeForm.toOrgNode()
        orgNode = orgNodeRepository.save(orgNode)
        /**
         * 分配主管理员
         */
        val userOrgNode = UserOrgNode().apply {
            this.orgNode = orgNode
            this.user = user
            this.level = Level.MAINADMIN
        }
        userOrgNodeRepository.save(userOrgNode)
        return Responses.ok(orgNode)
    }

    /**
     * 创建部门节点
     */
    override fun createDepartmentNode(orgNodeForm: OrgNodeForm, user: User): Response<OrgNode> {
        val orgOptional = orgNodeRepository.findById(orgNodeForm.parentId!!)
        if (orgOptional.isEmpty) {
            return Responses.fail("不存在id为${orgNodeForm.parentId}的节点")
        }

        // 进行主管理员的权限验证。
        this.guardMainAdmin(orgOptional.get(), user);

        // 保存该节点
        var orgNode = orgNodeForm.toOrgNode()
        orgNode = orgNodeRepository.save(orgNode)
        return Responses.ok(orgNode)
    }

    /**
     * 创建所有组织的概览信息
     */
    override fun all(): List<OrgSummary> {
        return orgNodeRepository.findAllByParentId(null).map { this.getOrganizationBase(it) }
    }
}