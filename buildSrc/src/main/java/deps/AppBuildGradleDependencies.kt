package deps

import deps.DependencyVersions.GRADLE_VERSION
import deps.DependencyVersions.HILT_VERSION
import deps.DependencyVersions.KOTLIN_VERSION

object AppBuildGradleDependencies {

    const val ANDROID_GRADLE_PLUGIN = "com.android.tools.build:gradle:$GRADLE_VERSION"
    const val KOTLIN_PLUGIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:$KOTLIN_VERSION"

    const val HILT_PLUGIN = "com.google.dagger:hilt-android-gradle-plugin:$HILT_VERSION"

}