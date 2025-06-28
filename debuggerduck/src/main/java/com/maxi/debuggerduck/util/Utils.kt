package com.maxi.debuggerduck.util

import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.io.File
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

internal object Utils {

    fun <T> writeLogsToFile(
        logs: List<T>,
        filesDir: File,
        fileName: String,
        scope: CoroutineScope
    ) {
        val gson = Gson()
        val logsString = gson.toJson(logs)

        scope.launch {
            val file = File(
                filesDir,
                fileName
            )

            if (file.exists()) {
                file.writeText("") //clear file contents
            }
            file.appendText(logsString)
        }
    }

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