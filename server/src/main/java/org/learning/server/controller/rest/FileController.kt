package org.learning.server.controller.rest

import org.learning.server.model.annotation.NoLogin
import org.learning.server.model.common.Response
import org.learning.server.model.common.ResponseTokens
import org.learning.server.model.common.Responses
import org.learning.server.service.impl.FileService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.ClassPathResource
import org.springframework.http.MediaType.IMAGE_JPEG_VALUE
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.io.File
import javax.servlet.http.HttpServletResponse

@Controller
@RequestMapping("api/file")
class FileController {
    @Value("\${spring.servlet.multipart.location}")
    lateinit var uploadDir: String

    @Autowired
    lateinit var fileService: FileService

    @PostMapping("/upload/img")
    @ResponseBody
    fun upload(@RequestParam("file") file: MultipartFile): Response<String> {
        if (file.isEmpty) {
            return Responses.fail("文件不能为空")
        }
        return Responses.ok(data = fileService.executeUpload(file, "img", uploadDir))
    }

    @PostMapping("/upload/video")
    @ResponseBody
    fun updateVideo(@RequestParam("file") file: MultipartFile): Response<String> {
        if (file.isEmpty) {
            return Responses.fail("文件不能为空")
        }
        return Responses.ok(data = fileService.executeUpload(file, "video", uploadDir))
    }

    @PostMapping("/upload/ppt")
    @ResponseBody
    fun uploadPPT(@RequestParam("file") file: MultipartFile): Response<String> {
        if (file.isEmpty) {
            return Responses.fail("文件不能为空")
        }

        return Responses.ok(data = fileService.executeUpload(file, "ppt", uploadDir))
    }

    @GetMapping("/img/{name}", produces = [IMAGE_JPEG_VALUE])
    @NoLogin
    fun getImage(@PathVariable name: String, response: HttpServletResponse) {
        println(name)
        val image = File("$uploadDir/img", name)
        response.contentType = "image/jpeg"
        if (image.exists() && image.isFile) {
            response.outputStream.use { it.write(image.readBytes()) }
        } else {
            response.outputStream.use {
                it.write(ClassPathResource("static/img/mockup5.jpg").inputStream.use { res -> res.readAllBytes() })
            }
        }
    }

    @GetMapping("/img/course/{name}", produces = [IMAGE_JPEG_VALUE])
    @NoLogin
    fun getCourseImage(@PathVariable name: String, response: HttpServletResponse) {
        response.outputStream.use { it.write(ClassPathResource("static/img/course/$name").inputStream.use { res -> res.readAllBytes() }) }
    }

    @RequestMapping("/ppt/size/{name}", method = [RequestMethod.GET, RequestMethod.POST])
    @ResponseBody
    fun getPptPageSize(@PathVariable name:String): Response<Int> {
        val pptFile = File("${uploadDir}ppt/$name")
        return fileService.pptGetPageSize(pptFile)
    }

    @GetMapping("/img/ppt/{name}/{index}", produces = [IMAGE_JPEG_VALUE])
    @NoLogin
    fun getPptImage(@PathVariable name: String, @PathVariable index: Int, response: HttpServletResponse) {
        val partFile = "${name}/${index}.png"
        val pptFile = File("${uploadDir}ppt/$name")
        val res = fileService.pptToImage(pptFile, uploadDir, index)
        if (res.code == ResponseTokens.ok.code) {
            response.outputStream.use {
                it.write(File("${uploadDir}pptView/${res.data!!}").readBytes())
            }
        } else {
            response.outputStream.use {
                it.write(ClassPathResource("static/img/mockup5.jpg").inputStream.use { res -> res.readAllBytes() })
            }
        }
    }
}