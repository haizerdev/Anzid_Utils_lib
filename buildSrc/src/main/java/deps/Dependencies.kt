package deps

import deps.DependencyVersions.HILT_ANDROIDX_VERSION
import deps.DependencyVersions.HILT_VERSION
import deps.DependencyVersions.LIFECYCLE
import deps.DependencyVersions.ROOM

object Dependencies {
    const val KOTLIN = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${DependencyVersions.KOTLIN_VERSION}"
    const val KOTLIN_POET = "com.squareup:kotlinpoet:${DependencyVersions.KOTLIN_POET}"

    object Dagger {
        const val MAIN = "com.google.dagger:dagger:${DependencyVersions.DAGGER_VERSION}"
        const val ANDROID = "com.google.dagger:dagger-android:${DependencyVersions.DAGGER_VERSION}"
        const val COMPILER = "com.google.dagger:dagger-compiler:${DependencyVersions.DAGGER_VERSION}"
    }

    object Hilt {
        const val ANDROID = "com.google.dagger:hilt-android:${HILT_VERSION}"
        const val COMPILER = "com.google.dagger:hilt-compiler:$HILT_VERSION"

        const val VIEW_MODEL = "androidx.hilt:hilt-lifecycle-viewmodel:${HILT_ANDROIDX_VERSION}"
        const val ANDROIDX_COMPILER = "androidx.hilt:hilt-compiler:$HILT_ANDROIDX_VERSION"
    }

    object Room {
        const val RUNTIME = "androidx.room:room-runtime:${ROOM}"
        const val KTX = "androidx.room:room-ktx:${ROOM}"
        const val COMPILER = "androidx.room:room-compiler:$ROOM"
    }

    object Lifecycle {
        const val EXTENSIONS = "androidx.lifecycle:lifecycle-extensions:${LIFECYCLE}"
        const val JAVA8 = "androidx.lifecycle:lifecycle-common-java8:${LIFECYCLE}"
        const val VIEW_MODEL_KTX = "androidx.lifecycle:lifecycle-viewmodel-ktx:${LIFECYCLE}"
        const val LIVEDATA_KTX = "androidx.lifecycle:lifecycle-livedata-ktx:${LIFECYCLE}"
    }
}