package com.maxi.debuggerduck.util

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object Utils {

    fun getCurrentDateTime(): String {
        val currentDataTime = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        return currentDataTime.format(formatter)
    }

    fun getCurrentDate(): String {
        val date = LocalDate.now()
        return "$date"
    }
}