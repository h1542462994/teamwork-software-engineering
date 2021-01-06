package org.learning.server.controller.rest

import org.learning.server.entity.Organization
import org.learning.server.model.common.Response
import org.learning.server.model.common.Responses
import org.learning.server.service.IOrgService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

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
}