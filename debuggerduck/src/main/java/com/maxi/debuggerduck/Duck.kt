package com.maxi.debuggerduck

import android.util.Log
import com.maxi.debuggerduck.util.LogLevel

object Duck {

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