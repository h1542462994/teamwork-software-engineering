package org.learning.server.service

import org.learning.server.entity.OrgNode
import org.learning.server.entity.Organization
import org.learning.server.entity.User
import org.learning.server.entity.base.OrganizationBase
import org.learning.server.entity.base.UserBase
import org.learning.server.form.OrgNodeForm
import org.learning.server.model.common.Response
import org.learning.server.model.complex.OrgSummary
import org.learning.server.model.complex.OrganizationGrouped
import java.util.*

interface IOrgService {
    @Deprecated("")
    fun findAll(): Iterable<Organization>

    @Deprecated("")
    fun findById(id: Int): Optional<Organization>

    @Deprecated("")
    fun grouped(user: User): OrganizationGrouped

    @Deprecated("")
    fun userInviteOrganization(orgId: Int, user: User): Response<OrganizationBase>

    @Deprecated("")
    fun getInvitesById(orgId: Int): List<UserBase>

    fun all(): Iterable<OrgSummary>
    fun list(user: User): Iterable<OrgSummary>
    fun guardMainAdmin(orgNode: OrgNode, user: User)
    fun create(orgNodeForm: OrgNodeForm, user: User): Response<OrgNode>
    fun delete(orgId: Int, user: User): Response<Any>
    fun get(orgId: Int, user: User): Response<OrgSummary>
}