package com.anzid.utils.helpers

fun <T> tryOrNull(block: () -> T) = try {
    block()
} catch (e: Throwable) {
    null
}