package com.maxi.debuggerduck.util

internal object LogLevel {

    enum class Icon(val icon: String) {
        VERBOSE("🔍"),
        DEBUG("🐞"),
        INFO("ℹ️"),
        WARNING("⚠️"),
        ERROR("❌")
    }
}