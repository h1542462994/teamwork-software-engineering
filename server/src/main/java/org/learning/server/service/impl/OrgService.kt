package org.learning.server.service.impl

import org.learning.server.entity.*
import org.learning.server.entity.base.OrganizationBase
import org.learning.server.entity.base.UserBase
import org.learning.server.entity.enums.Level
import org.learning.server.exception.NoAllowedException
import org.learning.server.form.OrgNodeForm
import org.learning.server.model.common.Response
import org.learning.server.model.common.Responses
import org.learning.server.model.complex.OrgNodeSummary
import org.learning.server.model.complex.OrgSummary
import org.learning.server.model.complex.OrganizationGrouped
import org.learning.server.service.IOrgService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import kotlin.collections.HashSet
import kotlin.math.max
import org.learning.server.common.MergeExtension.merge
import org.learning.server.model.complex.UserInfo
import org.learning.server.repository.*

@Service
class OrgService : IOrgService {
    //region inject components
    @Autowired
    lateinit var organizationRepository: OrganizationRepository

    @Autowired
    lateinit var userOrganizationInvitationRepository: UserOrganizationInvitationRepository

    @Autowired
    lateinit var orgNodeRepository: OrgNodeRepository

    @Autowired
    lateinit var userOrgNodeRepository: UserOrgNodeRepository

    @Autowired
    lateinit var userRepository: UserRepository
    //endregion

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
        if (organization.isEmpty) {
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
        return userOrganizationInvitationRepository.findAllByOrganizationAndInverse(organization, true)
            .map { it.toUserBase() }.distinct()
    }

    //region tool functions

    /**
     * 检测用户是否能够是该节点所在组织的主管理员，如果不是则抛出异常
     * @throws NoAllowedException 没有通过验证
     */
    override fun guardMainAdmin(orgNode: OrgNode, user: User) {
        // 向上查找组织节点
        val org = this.getOrganizationBase(this.getOrganizationOfNode(orgNode))
        if (org != null && org.owner.uid != user.uid) {
            throw NoAllowedException("你不是组织主管理员，无法进行此操作")
        }
    }

    override fun guardVisit(orgNode: OrgNode, user: User) {
        val org = this.getOrganizationBase(this.getOrganizationOfNode(orgNode))!!
        if (org.owner.uid == user.uid) {
            // do Nothing
            return
        }
        val userOrgNodes = userOrgNodeRepository.findAllByUser(user).filter {
            this.getOrganizationOfNode(it.orgNode).id == org.id
        }
        if (userOrgNodes.isEmpty()) {
            throw NoAllowedException("你不再这个组织内")
        }
    }

    /**
     * 根据一个节点查找其组织结构的根节点
     * TODO: Extend [getPathOfOrgNode]
     */
    private fun getOrganizationOfNode(orgNode: OrgNode): OrgNode {
        var current = orgNode
        while (current.parentId != null) {
            current = orgNodeRepository.findById(current.parentId!!).get()
        }

        return current
    }

    /**
     * 通过一个节点查找其根节点和一级子节点
     * TODO: Extend [getPathOfOrgNode]
     */
    private fun getFirstAndSecondOfNode(orgNode: OrgNode): Pair<OrgNode, OrgNode?> {
        var prev: OrgNode? = null
        var current = orgNode
        while (current.parentId != null) {
            prev = current
            current = orgNodeRepository.findById(current.parentId!!).get()
        }

        return Pair(current, prev)
    }

    /**
     * 获取一个节点一直到根节点的路径
     */
    private fun getPathOfOrgNode(orgNode: OrgNode): OrgSummary {
        var orgNodeSummary: OrgNodeSummary? = null
        var current = orgNode
        while (current.parentId != null) {
            current = orgNodeRepository.findById(current.parentId!!).get()
            val temp = current.toOrgNodeSummaryPart().apply {
                if (orgNodeSummary != null){
                    children = hashSetOf(orgNodeSummary!!)
                }
            }
            orgNodeSummary = temp
        }

        return current.toOrgSummaryPart().apply {
            if (orgNodeSummary != null){
                owner = userOrgNodeRepository.findAllByOrgNodeAndLevel(orgNode, Level.MAINADMIN).first().user
                children = hashSetOf(orgNodeSummary)
            }
        }
    }

    /**
     * 查找组织（根节点）对应的概览数据
     * 包括组织的基础数据，一级部门的基础数据，组织主管理员。
     */
    private fun getOrganizationBase(orgNode: OrgNode): OrgSummary? {
        if (orgNode.parentId != null) {
            throw IllegalArgumentException("orgNode必须是根节点");
        }
        val userOrgNode = userOrgNodeRepository.findAllByOrgNodeAndLevel(orgNode, Level.MAINADMIN)
        if (userOrgNode.isEmpty()) {

            return null
        }
        return orgNode.toOrgSummaryPart().apply {
            owner = userOrgNode.first().user
            children = HashSet(orgNodeRepository.findAllByParentId(orgNode.id).map { it.toOrgNodeSummaryPart() })
        }
    }

    private fun getOrgNodeTree(orgNode: OrgNode, user: User, parent: OrgNodeSummary? = null): OrgNodeSummary {
        var level = 0

        userOrgNodeRepository.findByUserAndOrgNode(user, orgNode).apply {
            if (this.isPresent){
                level = this.get().level
            }
        }

        return if (orgNode.parentId == null) {
            orgNode.toOrgSummaryPart().apply {
                this.level = level
                owner = userOrgNodeRepository.findAllByOrgNodeAndLevel(orgNode, Level.MAINADMIN).first().user
                children = HashSet(orgNodeRepository.findAllByParentId(orgNode.id).map { getOrgNodeTree(it, user, this) })
            }
        } else {
            orgNode.toOrgNodeSummaryPart().apply {
                this.level = max(level, parent?.level ?: 0)
                children = HashSet(orgNodeRepository.findAllByParentId(orgNode.id).map { getOrgNodeTree(it, user, this) })
            }
        }
    }

    /**
     * 获取此节点以及其所有子孙节点
     * @return 所有节点的平铺结果
     */
    private fun getFlatOrgNodesOfOrgNode(orgNode: OrgNode): List<OrgNode> {
        val orgNodeList = LinkedList<OrgNode>()
        val orgNodeResultList = LinkedList<OrgNode>()
        orgNodeList.add(orgNode)
        orgNodeResultList.add(orgNode)
        while (orgNodeList.isNotEmpty()) {
            val current = orgNodeList.first
            orgNodeList.remove(current)
            // 查找所有直接子节点
            val children = orgNodeRepository.findAllByParentId(current.id)
            orgNodeList.addAll(children)
            orgNodeResultList.addAll(children)
        }
        return orgNodeResultList
    }

    /**
     * 获取此节点以及所有子节点开设的课程，依赖于[getFlatOrgNodesOfOrgNode]
     */
    private fun getFlatCourseOpenOfOrgNode(orgNode: OrgNode): List<CourseOpen> {
        TODO("Not yet implemented")
    }


    //endregion

    //region services
    /**
     * 创建所有组织的概览信息
     */
    override fun all(): Iterable<OrgSummary> {
        return orgNodeRepository.findAllByParentId(null).mapNotNull { this.getOrganizationBase(it) }
    }

    override fun list(user: User): Iterable<OrgSummary> {
        // 查找用户所在的组织节点
        val userOrgNodes = userOrgNodeRepository.findAllByUser(user)
        val orgs = HashSet<OrgSummary>()
        for (userOrgNode in userOrgNodes) {
            val (org, dep) = this.getFirstAndSecondOfNode(userOrgNode.orgNode)
            orgs.add(org.toOrgSummaryPart().apply {
                this.owner = this@OrgService.getOrganizationBase(org)!!.owner
            })
            val orgSummary = orgs.find { it.id == org.id }!!
            if (dep != null) {
                orgSummary.children.add(dep.toOrgNodeSummaryPart())
            }
            orgSummary.level = max(orgSummary.level, userOrgNode.level)
        }

        for (org in orgs) {
            // 如果是管理员，则添加所有的部门（相当于可见）
            if (org.level > 0) {
                org.children.addAll(orgNodeRepository.findAllByParentId(org.id).map { it.toOrgNodeSummaryPart() })
            }
        }

        return orgs
    }

    override fun get(orgId: Int, user: User): Response<OrgSummary> {
        val orgOptional = orgNodeRepository.findById(orgId)
        if (orgOptional.isEmpty) {
            return Responses.fail("不存在该部门")
        } else if (orgOptional.get().parentId != null){
            return Responses.fail("仅能查询部门节点")
        }

        // 查找用户所在的节点（指定部门）
        val userOrgNodes = userOrgNodeRepository.findAllByUser(user).filter {
            getOrganizationOfNode(it.orgNode).id == orgId
        }

        if (userOrgNodes.isEmpty()) {
            return Responses.fail("你不在该部门")
        }

        val maxLevel = userOrgNodes.maxOf { it.level }
        return if (maxLevel == 0) {
            val merged = userOrgNodes.map { this.getPathOfOrgNode(it.orgNode) }.merge()
            Responses.ok(merged.first() as OrgSummary)
        } else {
            Responses.ok(getOrgNodeTree(orgNodeRepository.findById(orgId).get(), user) as OrgSummary)
        }
    }

    /**
     * 更改一个节点的信息
     */
    override fun update(orgId: Int, orgNodeForm: OrgNodeForm, user: User): Response<OrgSummary> {
        val org = getEntity(orgId)
        this.guardMainAdmin(org, user)
        org.name = orgNodeForm.name!!
        org.description = orgNodeForm.description!!
        org.public = orgNodeForm.public!!
        orgNodeRepository.save(org)

        return this.get(orgId, user)
    }

    /**
     * 获取一个节点下面的所有人员
     */
    override fun getPersons(orgId: Int, user: User): List<UserInfo> {
        val orgNode = getEntity(orgId)
        this.guardVisit(orgNode, user)
        return getPersonsInner(orgNode, 0)
    }

    private fun getPersonsInner(orgNode: OrgNode, depth: Int) : List<UserInfo> {
        val userList: LinkedList<UserInfo> = LinkedList()
        val userOrgNodes = userOrgNodeRepository.findAllByOrgNode(orgNode)
        userList.addAll(userOrgNodes.map { it.toUserInfo(depth) })
        val orgNodesChildren = orgNodeRepository.findAllByParentId(orgNode.id)
        orgNodesChildren.forEach {
            userList.addAll(this.getPersonsInner(it, depth + 1))
        }
        return userList.merge().map { it as UserInfo }
    }

    override fun removePerson(orgId: Int, personUid: String, user: User): Response<User> {
        TODO("Not yet implemented")
    }

    override fun exitPerson(orgId: Int, user: User): Response<Any> {
        TODO("Not yet implemented")
    }

    override fun inviteList(orgId: Int, user: User): Response<UserOrgNodeInvitation> {
        TODO("Not yet implemented")
    }

    override fun orgInvitePerson(orgId: Int, personUid: String, user: User): Response<Any> {
        TODO("Not yet implemented")
    }

    override fun personInviteOrg(orgId: Int, personUid: String, user: User): Response<Any> {
        TODO("Not yet implemented")
    }

    override fun processInvite(inviteId: Int, user: User, accept: Boolean): Response<Any> {
        TODO("Not yet implemented")
    }

    override fun changeLevel(orgId: Int, personUid: String, level: Int): Response<Any> {
        TODO("Not yet implemented")
    }

    private fun getEntity(orgId: Int): OrgNode {
        val orgOptional = orgNodeRepository.findById(orgId)
        if (orgOptional.isEmpty) {
            throw NoAllowedException("不存在id为${orgId}的节点")
        }
        return orgOptional.get()
    }

    /**
     * 创建节点，具体由[createOrganization]和[createDepartmentNode]节点实现
     */
    override fun create(orgNodeForm: OrgNodeForm, user: User): Response<OrgNode> {
        return if (orgNodeForm.parentId == null) {
            // 如果没有指定父节点则视为创建组织
            this.createOrganization(orgNodeForm, user)
        } else {
            // 如果指定了父节点则视为创建部门节点
            this.createDepartmentNode(orgNodeForm, user)
        }
    }

    /**
     * 创建组织节点
     */
    private fun createOrganization(orgNodeForm: OrgNodeForm, user: User): Response<OrgNode> {
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
    private fun createDepartmentNode(orgNodeForm: OrgNodeForm, user: User): Response<OrgNode> {
        var orgNode = getEntity(orgNodeForm.parentId!!)

        // 进行主管理员的权限验证。
        this.guardMainAdmin(orgNode, user);

        // 保存该节点
        orgNode = orgNodeForm.toOrgNode()
        orgNode = orgNodeRepository.save(orgNode)
        return Responses.ok(orgNode)
    }

    /**
     * 删除一个节点，具体由[deleteOrganization]和[deleteDepartmentNode]来实现
     */
    override fun delete(orgId: Int, user: User): Response<Any> {
        val orgOptional = orgNodeRepository.findById(orgId)
        if (orgOptional.isEmpty) {
            return Responses.fail("不存在id为${orgId}的节点")
        }
        val orgNode = orgOptional.get()
        this.guardMainAdmin(orgNode,user)


        if (orgNode.parentId == null) {
            deleteOrganization(orgNode);
        } else {
            deleteDepartmentNode(orgNode);
        }

        return Responses.ok()
    }

    /**
     * 删除一个组织
     */
    private fun deleteOrganization(orgNode: OrgNode) {
        val depNodes = orgNodeRepository.findAllByParentId(orgNode.id)
        depNodes.forEach {
            deleteDepartmentNode(it)
        }
        val userOrgNodes = userOrgNodeRepository.findAllByOrgNode(orgNode)
        userOrgNodeRepository.deleteAll(userOrgNodes)
        orgNodeRepository.delete(orgNode)

        // TODO: 发送通知
    }

    /**
     * 删除一个部门节点
     */
    private fun deleteDepartmentNode(orgNode: OrgNode) {
        val depNodes = orgNodeRepository.findAllByParentId(orgNode.id)
        // move the person
        val userOrgNodes = userOrgNodeRepository.findAllByOrgNode(orgNode)
        userOrgNodes.forEach {
            it.level = Level.NORMAL
            it.orgNode = getOrganizationOfNode(orgNode)
        }
        userOrgNodeRepository.saveAll(userOrgNodes)

        depNodes.forEach {
            deleteDepartmentNode(it)
        }

        orgNodeRepository.delete(orgNode)

        // TODO: 发送通知
    }

    override fun searchPerson(orgId: Int, query: String, user: User): Response<Iterable<User>>{
        val org = getEntity(orgId)
        this.guardVisit(org, user)
        val existUsers = this.getPersonsInner(org, 0);

        val users = userRepository.findAllByNameLikeOrUidLikeOrderByUid("%${query}%", "%${query}%");
        return Responses.ok(
            users.filter {
                existUsers.find { it2 -> it2.uid == it.uid } == null
            }
        )
    }
    //endregion
}