package com.anzid.utils.utils

import com.anzid.utils.BuildConfig
import com.anzid.utils.Constants

fun isInternalBuild() = BuildConfig.FLAVOR == Constants.FLAVOR_INTERNAL

fun isPlayBuild() = BuildConfig.FLAVOR == Constants.FLAVOR_PLAY

fun isReleaseBuildType() = BuildConfig.BUILD_TYPE == Constants.BUILD_TYPE_RELEASE

fun isDebugBuildType() = BuildConfig.BUILD_TYPE == Constants.BUILD_TYPE_DEBUG

fun isInternalRelease() = isInternalBuild() && isReleaseBuildType()

fun isPlayRelease() = isPlayBuild() && isReleaseBuildType()