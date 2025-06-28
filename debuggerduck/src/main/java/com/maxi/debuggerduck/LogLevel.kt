package com.maxi.debuggerduck

internal object LogLevel {

    enum class Icon(val icon: String) {
        VERBOSE("🔍"),
        DEBUG("🐞"),
        INFO("ℹ️"),
        WARNING("⚠️"),
        ERROR("❌")
    }
}