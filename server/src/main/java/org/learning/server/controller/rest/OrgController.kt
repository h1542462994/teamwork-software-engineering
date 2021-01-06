package org.learning.server.controller.rest

import org.learning.server.common.SessionHelper
import org.learning.server.entity.Organization
import org.learning.server.model.common.Response
import org.learning.server.model.common.Responses
import org.learning.server.model.complex.OrganizationGrouped
import org.learning.server.service.IOrgService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/api/org")
class OrgController {
    @Autowired
    lateinit var orgService: IOrgService

    @PostMapping("/all")
    fun all(): Response<Iterable<Organization>> {
        return Responses.ok(orgService.findAll())
    }

    @PostMapping("/get")
    fun get(id: Int): Response<Organization?> {
        val optional = orgService.findById(id)
        return if (optional.isPresent) {
            Responses.ok(optional.get())
        } else {
            Responses.fail()
        }
    }

    @PostMapping("/grouped")
    fun grouped(request: HttpServletRequest): Response<OrganizationGrouped> {
        val user = SessionHelper.of(request).user()
        return if (user != null){
            Responses.ok(orgService.grouped(user))
        } else {
            Responses.fail()
        }
    }
}