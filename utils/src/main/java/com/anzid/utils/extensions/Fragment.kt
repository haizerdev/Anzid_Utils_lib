package com.anzid.utils.extensions

import android.view.View
import androidx.annotation.MainThread
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.createViewModelLazy
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

fun Fragment.requireAppCompatActivity(): AppCompatActivity = requireActivity() as AppCompatActivity

fun Fragment.getSupportActionBarOrNull(): ActionBar? = requireAppCompatActivity().supportActionBar

fun Fragment.showKeyboard(view: View, delay: Long = 0) {
    requireActivity().showKeyboard(view, delay)
}

fun Fragment.hideKeyboard(view: View? = null) {
    requireActivity().hideKeyboard(view)
}

fun Fragment.toggleKeyboard() {
    requireActivity().toggleKeyboard()
}

@MainThread
inline fun <reified VM : ViewModel> Fragment.parentFragmentViewModels(
        noinline factoryProducer: (() -> ViewModelProvider.Factory)? = null
) = createViewModelLazy(VM::class, { requireParentFragment().viewModelStore }, factoryProducer)
