package com.maxi.debuggerduck

import android.content.Context
import android.util.Log
import com.maxi.debuggerduck.data.local.DebuggerDuckDatabase
import com.maxi.debuggerduck.data.local.model.Debug
import com.maxi.debuggerduck.util.LogLevel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class Duck private constructor(
    context: Context
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
            context: Context
        ): Duck {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Duck(context).also {
                    INSTANCE = it
                }
            }
        }
    }

    fun v(tag: String, message: String) {
        val logMessage = "${LogLevel.Icon.VERBOSE.icon} $message"
        Log.v(tag, logMessage)
    }

    fun d(tag: String, message: String) {
        val logMessage = "${LogLevel.Icon.DEBUG.icon} $message"
        Log.d(tag, logMessage)
    }

    fun i(tag: String, message: String) {
        val logMessage = "${LogLevel.Icon.INFO.icon} $message"
        Log.i(tag, logMessage)
    }

    fun w(tag: String, message: String) {
        val logMessage = "${LogLevel.Icon.WARNING.icon} $message"
        Log.w(tag, logMessage)
    }

    fun e(tag: String, message: String) {
        val logMessage = "${LogLevel.Icon.ERROR.icon} $message"
        Log.e(tag, logMessage)
    }
}