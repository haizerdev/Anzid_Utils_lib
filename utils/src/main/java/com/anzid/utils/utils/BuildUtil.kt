package com.anzid.utils.utils

import com.anzid.utils.BuildConfig
import com.anzid.utils.Constants

fun isReleaseBuildType() = BuildConfig.BUILD_TYPE == Constants.BUILD_TYPE_RELEASE

fun isDebugBuildType() = BuildConfig.BUILD_TYPE == Constants.BUILD_TYPE_DEBUG