package com.anzid.utils.android

import android.content.Context
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import javax.inject.Inject

abstract class BaseViewModel : ViewModel(), LifecycleObserver {

    @Inject
    protected lateinit var appContext: Context

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    open fun onCreate() = Unit

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    open fun onStart() = Unit

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    open fun onResume() = Unit

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    open fun onPause() = Unit

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    open fun onStop() = Unit
}