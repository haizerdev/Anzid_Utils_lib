package deps

import org.gradle.api.JavaVersion

object AppSettings {
    const val APP_NAME = "com.anzid.portfolioapp"

    const val VERSION_CODE = 1
    const val VERSION_NAME = "0.0.1"

    const val TARGET_SDK = 30
    const val COMPILE_SDK = 30
    const val MIN_SDK = 21

    val JAVA_VERSION = JavaVersion.VERSION_1_8
}