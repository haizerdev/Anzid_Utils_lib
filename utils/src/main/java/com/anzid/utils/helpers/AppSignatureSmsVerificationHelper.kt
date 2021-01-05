package com.anzid.utils.helpers

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.util.Base64
import android.util.Log
import com.anzid.utils.BuildConfig
import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*

/**
 * This is a helper class to generate your message hash to be included in your SMS message.
 *
 * Without the correct hash, your app won't receive the message callback. This only needs to be
 * generated once per app and stored. Then you can remove this helper class from your code.
 *
 * See https://stackoverflow.com/questions/53849023/android-sms-retriever-api-computing-apps-hash-string-problem
 * See https://developers.google.com/identity/sms-retriever/verify#generating_a_one-time_code
 */
class AppSignatureSmsVerificationHelper(context: Context) {
    private val hashType = "SHA-256"
    private val numHashedBytes = 9
    private val numBase64Char = 11

    /**
     * Get all the app signatures for the current package
     */
    private val appSignatures: ArrayList<String>
    private val appSignature: String?
        get() = appSignatures.firstOrNull()

    init {
        val appCodes = ArrayList<String>()
        try {
            val packageName = context.packageName
            val packageManager = context.packageManager
            val signatures =
                    if (Build.VERSION.SDK_INT >= 28) {
                        packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNING_CERTIFICATES).signingInfo.apkContentsSigners
                    } else {
                        @Suppress("DEPRECATION")
                        packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES).signatures
                    }
            // For each signature create a compatible hash
            for (signature in signatures) {
                val hash = hash(packageName, signature.toCharsString())
                if (hash != null) {
                    appCodes.add(String.format("%s", hash))
                }
                Log.v(TAG, "Hash $hash")
            }
        } catch (e: PackageManager.NameNotFoundException) {
            Log.w(TAG, "PackageManager error", e)
        }
        appSignatures = appCodes
    }

    fun loggedAppSignature() {
        Log.v(TAG, "app signature hash for type-${BuildConfig.BUILD_TYPE} and flavor-${BuildConfig.FLAVOR} : $appSignature")
    }

    /**
     * Generates the hash by running sha on the string 'package hash'.
     */
    @SuppressLint("ObsoleteSdkInt")
    private fun hash(packageName: String, signature: String): String? {
        val appInfo = "$packageName $signature"
        try {
            val messageDigest = MessageDigest.getInstance(hashType)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                messageDigest.update(appInfo.toByteArray(StandardCharsets.UTF_8))
            }
            var hashSignature = messageDigest.digest()

            // truncated into NUM_HASHED_BYTES
            hashSignature = Arrays.copyOfRange(hashSignature, 0, numHashedBytes)
            // encode into Base64
            var base64Hash = Base64.encodeToString(hashSignature, Base64.NO_PADDING or Base64.NO_WRAP)
            base64Hash = base64Hash.substring(0, numBase64Char)
            // Log.d(TAG, String.format("pkg: %s -- hash: %s", packageName, base64Hash))
            return base64Hash
        } catch (e: NoSuchAlgorithmException) {
            Log.w(TAG, "hash:NoSuchAlgorithm", e)
        }
        return null
    }

    companion object {
        private val TAG = AppSignatureSmsVerificationHelper::class.java.simpleName

        fun loggedAppSignature(context: Context) {
            AppSignatureSmsVerificationHelper(context).loggedAppSignature()
        }
    }
}