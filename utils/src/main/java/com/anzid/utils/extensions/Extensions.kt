package com.anzid.utils.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlin.math.ln
import kotlin.math.pow

/**
 * example of use: 13743456 bytes -> 1.6 Mb
 * @param si - use metric system 1000 K or else 'computer 1024 K'
 */
fun Long.humanReadableByteCount(si: Boolean = true): String {
    val unit = if (si) 1000 else 1024
    if (this < unit) return "$this B"
    val exp = (ln(this.toDouble()) / ln(unit.toDouble())).toInt()
    val pre = (if (si) "kMGTPE" else "KMGTPE")[exp - 1] + if (si) "" else "i"
    return String.format("%.1f %sB", this / unit.toDouble().pow(exp.toDouble()), pre)
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}