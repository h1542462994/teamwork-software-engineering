package org.learning.server.common

import java.sql.Timestamp
import java.time.LocalDateTime

object TimeStampHelper {
    fun now(): Timestamp {
        return Timestamp.valueOf(LocalDateTime.now().plusHours(8))
    }
}