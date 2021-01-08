package org.learning.server.entity

import javax.persistence.*

@Entity
class CourseRecordMedia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = -1
    @ManyToOne
    var media: Media = Media()
    var process: Int = 0
    /**
     * 记录ppt的页数，视频的时间等等
     */
    var data: String = ""
}