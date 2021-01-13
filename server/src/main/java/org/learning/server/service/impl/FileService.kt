package org.learning.server.service.impl

import org.learning.server.service.IFileService
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.util.*

@Service
class FileService : IFileService {
    override fun executeUpload(file: MultipartFile, type: String, uploadDir: String): String {

        val suffix = file.originalFilename!!.substring(file.originalFilename!!.lastIndexOf("."))
        val fileName: String = "${UUID.randomUUID()}${suffix}"
        val serverFile = File("${uploadDir}${type}/${fileName}")
        if (!serverFile.parentFile.exists()) {
            serverFile.parentFile.mkdirs()
        }
        file.transferTo(serverFile)
        return "${type}/${fileName}"
    }
}