package com.anzid.utils.logs

import android.util.Log
import com.anzid.utils.Constants

internal class DetailedLogger : Logger() {
    companion object {
        private const val MESSAGE_DEFAULT_LENGTH = 150
        private const val THREAD_DEFAULT_LENGTH = 50
        private const val CALL_DEFAULT_LENGTH = 50
    }

    override fun v(message: String?, t: Throwable?) {
        Log.v(Constants.LOG_TAG, createDetailMessage(message ?: Constants.EMPTY_STRING), t)
    }

    override fun d(message: String?, t: Throwable?) {
        Log.d(Constants.LOG_TAG, createDetailMessage(message ?: Constants.EMPTY_STRING), t)
    }

    override fun i(message: String?, t: Throwable?) {
        Log.i(Constants.LOG_TAG, createDetailMessage(message ?: Constants.EMPTY_STRING), t)
    }

    override fun w(message: String?, t: Throwable?) {
        Log.w(Constants.LOG_TAG, createDetailMessage(message ?: Constants.EMPTY_STRING), t)
    }

    override fun e(message: String?, t: Throwable?) {
        Log.e(Constants.LOG_TAG, createDetailMessage(message ?: Constants.EMPTY_STRING), t)
    }

    override fun wtf(message: String?, t: Throwable?) {
        Log.wtf(Constants.LOG_TAG, createDetailMessage(message ?: Constants.EMPTY_STRING), t)
    }

    override fun blockUntilAllWritesFinished() = Unit

    /**
     * Prepare message for log.
     * Output the next format:
     * MESSAGE   CALL_POINT   THREAD
     */
    private fun createDetailMessage(message: String = Constants.EMPTY_STRING): String {
        val messageLng = if (message.length > MESSAGE_DEFAULT_LENGTH) message.length
        else MESSAGE_DEFAULT_LENGTH
        val incomeMsg = String.format("%s" + "%2$" + (messageLng - message.length + 1) + "s", message, Constants.EMPTY_STRING)
        val threadLng = if (Thread.currentThread().name.length > THREAD_DEFAULT_LENGTH) Thread.currentThread().name.length else THREAD_DEFAULT_LENGTH
        val thread = String.format("%s" + "%2$" + (threadLng - Thread.currentThread().name.length + 1) + "s", Thread.currentThread().name, Constants.EMPTY_STRING)
        val trace = Thread.currentThread().stackTrace
        var callPoint: String

        callPoint = "(" + trace[6].toString().split("(")[1]


        val callPointLng = if (callPoint.length > CALL_DEFAULT_LENGTH) callPoint.length else CALL_DEFAULT_LENGTH
        callPoint = String.format("%s" + "%2$" + (callPointLng - callPoint.length + 1) + "s", callPoint, Constants.EMPTY_STRING)

        return callPoint + "\t\t\t" + incomeMsg + "\t\t\t" + thread + "\n"
    }
}
