package org.learning.server.service

import org.learning.server.model.common.Response
import org.springframework.web.multipart.MultipartFile
import java.io.File

interface IFileService {
    fun executeUpload(file: MultipartFile, type: String, uploadDir: String): String
    fun pptToImage(pptFile: File, uploadDir: String, index: Int): Response<String>
}