package org.learning.server.controller.rest

import org.learning.server.model.common.Response
import org.learning.server.model.common.Responses
import org.learning.server.service.impl.FileService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("api/file")
class FileController {
    @Value("\${spring.servlet.multipart.location}")
    lateinit var uploadDir: String

    @Autowired
    lateinit var fileService: FileService

    @PostMapping("/upload/img")
    fun upload(@RequestParam("file") file: MultipartFile): Response<String> {
        if (file.isEmpty) {
            return Responses.fail("文件不能为空")
        }
        return Responses.ok(data = fileService.executeUpload(file,"img", uploadDir))
    }
}