package com.maxi.debuggerduck

import android.content.Context
import android.util.Log
import com.maxi.debuggerduck.data.local.DebuggerDuckDatabase
import com.maxi.debuggerduck.data.local.model.Debug
import com.maxi.debuggerduck.data.local.model.Error
import com.maxi.debuggerduck.data.local.model.Info
import com.maxi.debuggerduck.data.local.model.Verbose
import com.maxi.debuggerduck.data.local.model.Warning
import com.maxi.debuggerduck.util.Constants.DEBUG_LOGS_FILE_NAME_PREFIX
import com.maxi.debuggerduck.util.Constants.ERROR_LOGS_FILE_NAME_PREFIX
import com.maxi.debuggerduck.util.Constants.INFO_LOGS_FILE_NAME_PREFIX
import com.maxi.debuggerduck.util.Constants.VERBOSE_LOGS_FILE_NAME_PREFIX
import com.maxi.debuggerduck.util.Constants.WARNING_LOGS_FILE_NAME_PREFIX
import com.maxi.debuggerduck.util.LogLevel
import com.maxi.debuggerduck.util.Utils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class Duck private constructor(
    context: Context,
    private val writeToFile: Boolean
) {

    private val appContext: Context
    private val duckDb: DebuggerDuckDatabase
    private val job: Job
    private val scope: CoroutineScope

    init {
        appContext = context.applicationContext
        duckDb = DebuggerDuckDatabase.getInstance(appContext)
        job = Job()
        scope = CoroutineScope(Dispatchers.IO + job)
    }

    companion object {
        @Volatile
        private var INSTANCE: Duck? = null

        fun getInstance(
            context: Context,
            writeToFile: Boolean = false,
            fileName: String = ""
        ): Duck {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Duck(
                    context,
                    writeToFile
                ).also {
                    INSTANCE = it
                }
            }
        }
    }

    fun v(tag: String, message: String) {
        val logMessage = "${LogLevel.Icon.VERBOSE.icon} $message"
        Log.v(tag, logMessage)
        if (writeToFile) {
            scope.launch {
                val dao = duckDb.verboseDao()
                dao.insertLog(
                    Verbose(
                        log = logMessage,
                        logTime = Utils.getCurrentDateTime()
                    )
                )
                val logs = dao.getLogs().first()
                val fileName = VERBOSE_LOGS_FILE_NAME_PREFIX + "_" + Utils.getCurrentDate() + ".txt"
                Utils.writeLogsToFile(
                    logs,
                    appContext.filesDir,
                    fileName,
                    scope
                )
            }
        }
    }

    fun d(tag: String, message: String) {
        val logMessage = "${LogLevel.Icon.DEBUG.icon} $message"
        Log.d(tag, logMessage)
        if (writeToFile) {
            scope.launch {
                val dao = duckDb.debugDao()
                dao.insertLog(
                    Debug(
                        log = logMessage,
                        logTime = Utils.getCurrentDateTime()
                    )
                )
                val logs = dao.getLogs().first()
                val fileName = DEBUG_LOGS_FILE_NAME_PREFIX + "_" + Utils.getCurrentDate() + ".txt"
                Utils.writeLogsToFile(
                    logs,
                    appContext.filesDir,
                    fileName,
                    scope
                )
            }
        }
    }

    fun i(tag: String, message: String) {
        val logMessage = "${LogLevel.Icon.INFO.icon} $message"
        Log.i(tag, logMessage)
        if (writeToFile) {
            scope.launch {
                val dao = duckDb.infoDao()
                dao.insertLog(
                    Info(
                        log = logMessage,
                        logTime = Utils.getCurrentDateTime()
                    )
                )
                val logs = dao.getLogs().first()
                val fileName = INFO_LOGS_FILE_NAME_PREFIX + "_" + Utils.getCurrentDate() + ".txt"
                Utils.writeLogsToFile(
                    logs,
                    appContext.filesDir,
                    fileName,
                    scope
                )
            }
        }
    }

    fun w(tag: String, message: String) {
        val logMessage = "${LogLevel.Icon.WARNING.icon} $message"
        Log.w(tag, logMessage)
        if (writeToFile) {
            scope.launch {
                val dao = duckDb.warningDao()
                dao.insertLog(
                    Warning(
                        log = logMessage,
                        logTime = Utils.getCurrentDateTime()
                    )
                )
                val logs = dao.getLogs().first()
                val fileName = WARNING_LOGS_FILE_NAME_PREFIX + "_" + Utils.getCurrentDate() + ".txt"
                Utils.writeLogsToFile(
                    logs,
                    appContext.filesDir,
                    fileName,
                    scope
                )
            }
        }
    }

    fun e(tag: String, message: String) {
        val logMessage = "${LogLevel.Icon.ERROR.icon} $message"
        Log.e(tag, logMessage)
        if (writeToFile) {
            scope.launch {
                val dao = duckDb.errorDao()
                dao.insertLog(
                    Error(
                        log = logMessage,
                        logTime = Utils.getCurrentDateTime()
                    )
                )
                val logs = dao.getLogs().first()
                val fileName = ERROR_LOGS_FILE_NAME_PREFIX + "_" + Utils.getCurrentDate() + ".txt"
                Utils.writeLogsToFile(
                    logs,
                    appContext.filesDir,
                    fileName,
                    scope
                )
            }
        }
    }

    fun close() {
        duckDb.close()
        scope.cancel()
    }
}