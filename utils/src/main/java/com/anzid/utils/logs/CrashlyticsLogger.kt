package com.anzid.utils.logs

import com.anzid.utils.utils.isDebugBuildType
import com.google.firebase.crashlytics.FirebaseCrashlytics

internal class CrashlyticsLogger : Logger() {

    companion object {
        private const val WARNING_TAG = "[WARNING]"
        private const val ERROR_TAG = "[ERROR]"
        private const val LOG_KEYWORD = "log"

        fun fillInfo(test: String) {
            if (isDebugBuildType().not()) FirebaseCrashlytics.getInstance().log(test)
        }
    }

    override fun w(message: String?, t: Throwable?) {
        prepareObjectForLog(WARNING_TAG, message, t)
    }

    override fun e(message: String?, t: Throwable?) {
        prepareObjectForLog(ERROR_TAG, message, t)
    }

    private fun prepareObjectForLog(tag: String, message: String?, t: Throwable?) {
        if (message.isNullOrBlank() && t == null) return
        var msg = tag
        message?.let { msg += " $it;" }
        t?.message?.let { msg += " $it;" }
        t?.cause?.message?.let { msg += " $it;" }
        val logThrowable = t?.let {
            Throwable(msg, it)
        } ?: Throwable(msg)

        //clean stackTrace
        logThrowable.stackTrace = logThrowable.stackTrace.drop(logThrowable.stackTrace.indexOfFirst {
            it.className.contains(LOG_KEYWORD, true).not() and
                    it.methodName.contains(LOG_KEYWORD, true).not()
        }).toTypedArray()

        sendToCrashlytics(logThrowable)
    }

    private fun sendToCrashlytics(t: Throwable) = FirebaseCrashlytics.getInstance().recordException(t)

    override fun blockUntilAllWritesFinished() = Unit
    override fun i(message: String?, t: Throwable?) = Unit
    override fun v(message: String?, t: Throwable?) = Unit
    override fun d(message: String?, t: Throwable?) = Unit
    override fun wtf(message: String?, t: Throwable?) = Unit
}