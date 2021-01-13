package org.learning.server.service

import org.springframework.web.multipart.MultipartFile

interface IFileService {
    fun executeUpload(file: MultipartFile, type: String, uploadDir: String): String
}