package com.anzid.utils.logs

import com.anzid.utils.utils.isDebugBuildType

fun initAnzidLog(isCrashlyticsLoggerInit: Boolean) {
    val loggers = mutableListOf<Logger>()
    if (isDebugBuildType()) loggers += DetailedLogger()
    if (isCrashlyticsLoggerInit) loggers += CrashlyticsLogger()

    AnzidLog.initialize(*loggers.toTypedArray())
}