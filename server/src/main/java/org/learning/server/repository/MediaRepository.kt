package org.learning.server.repository

import org.learning.server.entity.Chapter
import org.learning.server.entity.Media
import org.springframework.data.repository.CrudRepository

interface MediaRepository: CrudRepository<Media, Int> {
    fun findAllByChapter(chapter: Chapter): List<Media>
}