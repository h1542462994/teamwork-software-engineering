package org.learning.server.controller.rest

import org.learning.server.common.SessionHelper
import org.learning.server.entity.OrgNode
import org.learning.server.entity.Organization
import org.learning.server.entity.base.OrganizationBase
import org.learning.server.entity.base.UserBase
import org.learning.server.form.OrgNodeForm
import org.learning.server.model.annotation.NoUsed
import org.learning.server.model.common.Response
import org.learning.server.model.common.Responses
import org.learning.server.model.complex.OrgSummary
import org.learning.server.model.complex.OrganizationGrouped
import org.learning.server.service.IOrgService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest
import javax.validation.Valid

/**
 * 与组织有关的控制器
 */
@RestController
@RequestMapping("/api/org")
class OrgController {
    @Autowired
    lateinit var orgService: IOrgService

//    @PostMapping("/all")
//    @NoUsed
//    @Deprecated("")
//    fun all(): Response<Iterable<Organization>> {
//        return Responses.ok(orgService.findAll())
//    }

    /**
     * 获取一个org的详细信息，在/org/:{id}使用
     */
//    @PostMapping("/get")
//    @Deprecated("")
//    fun get(id: Int): Response<Organization?> {
//        val optional = orgService.findById(id)
//        return if (optional.isPresent) {
//            Responses.ok(optional.get())
//        } else {
//            Responses.fail()
//        }
//    }

    /**
     * 获取一个用户的org列表，在/org中使用
     */
    @PostMapping("/grouped")
    @Deprecated("")
    fun grouped(request: HttpServletRequest): Response<OrganizationGrouped> {
        val user = SessionHelper.of(request).user()
        return if (user != null){
            Responses.ok(orgService.grouped(user))
        } else {
            Responses.fail()
        }
    }

    /**
     * 用户申请加入一个组织或者取消申请加入一个组织
     */
    @PostMapping("/user_invite")
    @Deprecated("")
    fun userInviteOrganization(orgId: Int, request: HttpServletRequest): Response<OrganizationBase> {
        val user = SessionHelper.of(request).user()!!
        return orgService.userInviteOrganization(orgId, user)
    }

    /**
     * 获取用户申请加入组织的列表
     */
    @PostMapping("/invites/get")
    @Deprecated("")
    fun getInvitesById(orgId: Int, request: HttpServletRequest): Response<List<UserBase>> {
        val user = SessionHelper.of(request).user()!!
        // TODO: 加入权限验证
        return Responses.ok(orgService.getInvitesById(orgId));
    }

    /**
     * 创建一个架构节点
     */
    @PostMapping("/create")
    fun create(@Valid orgNodeForm: OrgNodeForm, request: HttpServletRequest): Response<OrgNode> {
        val user = SessionHelper.of(request).user()!!
        return orgService.create(orgNodeForm, user)
    }

    /**
     * 删除一个架构节点
     */
    @PostMapping("/delete")
    fun delete(orgId: Int, request: HttpServletRequest): Response<Any> {
        val user = SessionHelper.of(request).user()!!
        return orgService.delete(orgId, user)
    }

    /**
     * 获取所有组织的概览信息
     */
    @PostMapping("/all")
    fun all(): Response<List<OrgSummary>> {
        return Responses.ok(orgService.all())
    }


}