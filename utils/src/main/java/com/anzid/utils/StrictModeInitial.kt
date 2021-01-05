package com.anzid.utils

import android.os.Build
import android.os.StrictMode
import android.os.StrictMode.VmPolicy

fun initStrictMode(isInit: Boolean, isInitDeath: Boolean) {
    if (BuildConfig.DEBUG && isInit) {
        val policyBuilder = VmPolicy.Builder()
                .detectLeakedClosableObjects()
                .detectLeakedSqlLiteObjects()
                .detectActivityLeaks()
                .penaltyLog()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            policyBuilder.detectNonSdkApiUsage()
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            policyBuilder.detectUntaggedSockets()
            policyBuilder.detectContentUriWithoutPermission()
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            policyBuilder.detectCleartextNetwork()
        }

        if (isInitDeath) policyBuilder.penaltyDeath()
        StrictMode.setVmPolicy(policyBuilder.build())
    }
}