package com.anzid.utils.logs

internal abstract class Logger {
    abstract fun v(message: String?, t: Throwable?)
    abstract fun d(message: String?, t: Throwable?)
    abstract fun i(message: String?, t: Throwable?)
    abstract fun w(message: String?, t: Throwable?)
    abstract fun e(message: String?, t: Throwable?)
    abstract fun wtf(message: String?, t: Throwable?)
    abstract fun blockUntilAllWritesFinished()
}