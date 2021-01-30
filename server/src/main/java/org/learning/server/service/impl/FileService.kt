package org.learning.server.service.impl

import org.apache.poi.xslf.usermodel.XMLSlideShow
import org.apache.poi.xslf.usermodel.XSLFTextShape
import org.learning.server.entity.enums.MediaType
import org.learning.server.model.common.Response
import org.learning.server.model.common.Responses
import org.learning.server.service.IFileService
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.awt.Color
import java.awt.MediaTracker
import java.awt.geom.Rectangle2D
import java.awt.image.BufferedImage
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.*
import javax.imageio.ImageIO

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

    override fun pptGetPageSize(pptFile: File): Response<Int> {
        val inputStream = FileInputStream(pptFile)
        // 读取到pptSlide
        val xmlSlideShow = XMLSlideShow(inputStream)
        inputStream.close()
        return Responses.ok(data = xmlSlideShow.slides.size)
    }

    override fun pptToImage(pptFile: File, uploadDir: String, index: Int): Response<String> {
        val inputSteam = FileInputStream(pptFile)
        // 读取到pptSlide
        val xmlSlideShow = XMLSlideShow(inputSteam)
        inputSteam.close()
        // 获取ppt的大小
        val pageSize = xmlSlideShow.pageSize
        // 获取所有的幻灯片
        val slides = xmlSlideShow.slides
        if (index < 0 || index >= slides.size){
            return Responses.fail("index范围错误")
        }

        val partPath = "${pptFile.name}/${index}.png"
        val savePath = "${uploadDir}/pptView/${partPath}"
        val saveFile = File(savePath)
        // 如果存储这张图片则跳过
        if (!saveFile.exists()) {
            val image = BufferedImage(pageSize.width, pageSize.height, BufferedImage.TYPE_INT_RGB)
            val graphics = image.createGraphics()
            graphics.paint = Color.white
            graphics.fill(Rectangle2D.Float(0f, 0f, pageSize.width.toFloat(), pageSize.height.toFloat()))
            // 将slide填充的到图片
            slides[index].draw(graphics)
            if (!saveFile.parentFile.exists()) {
                saveFile.parentFile.mkdirs()
            }
            val outputStream = FileOutputStream(saveFile)

            ImageIO.write(image, "png", outputStream)
        }
        return Responses.ok(data = partPath)
    }
}