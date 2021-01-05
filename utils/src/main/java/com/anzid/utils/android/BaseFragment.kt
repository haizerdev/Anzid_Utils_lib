package com.anzid.utils.android

import android.graphics.Rect
import android.os.Bundle
import android.view.*
import androidx.annotation.ContentView
import androidx.annotation.LayoutRes
import androidx.fragment.app.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anzid.utils.extensions.parentFragmentViewModels
import javax.inject.Inject

abstract class BaseFragment<VM : BaseViewModel> : Fragment {

    constructor() : super()

    @ContentView
    constructor(@LayoutRes contentLayoutId: Int) : super(contentLayoutId)

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    protected open val viewModel: VM by lazy { ViewModelProvider(this, viewModelFactory)[getViewModelClass()] }

    private var rootLayout: View? = null
    private var keyboardListenersAttached = false

    private val keyboardLayoutListener = ViewTreeObserver.OnGlobalLayoutListener {
        rootLayout?.let {
            val rect = Rect().apply { it.getWindowVisibleDisplayFrame(this) }
            if (rect.bottom > it.rootView.height * 0.8) onHideKeyboard()
            else onShowKeyboard()
        }
    }

    protected abstract fun getViewModelClass(): Class<VM>

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initObservers()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(viewModel)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootLayout = super.onCreateView(inflater, container, savedInstanceState)
        return rootLayout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        rootLayout?.let {
            it.viewTreeObserver.removeOnGlobalLayoutListener(keyboardLayoutListener)
            keyboardListenersAttached = false
            rootLayout = null
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        childFragmentManager.fragments.filter(Fragment::isResumed).forEach { it.onCreateOptionsMenu(menu, inflater) }
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        childFragmentManager.fragments.filter(Fragment::isResumed).forEach { it.onOptionsItemSelected(item) }
        return super.onOptionsItemSelected(item)
    }

    protected fun attachKeyboardListeners() {
        if (keyboardListenersAttached) return
        rootLayout?.let {
            it.viewTreeObserver.addOnGlobalLayoutListener(keyboardLayoutListener)
            keyboardListenersAttached = true
        }
    }

    protected open fun initListeners() = Unit
    protected open fun initObservers() = Unit
    /**
     * To make this work, you need to call [attachKeyboardListeners] at first
     */
    protected open fun onShowKeyboard() = Unit

    protected open fun onHideKeyboard() = Unit

    protected inline fun <reified VM : ViewModel> daggerViewModels() = viewModels<VM>(factoryProducer = ::viewModelFactory)
    protected inline fun <reified VM : ViewModel> daggerActivityViewModels() = activityViewModels<VM>(factoryProducer = ::viewModelFactory)
    protected inline fun <reified VM : ViewModel> daggerParentFragmentViewModels() = parentFragmentViewModels<VM>(factoryProducer = ::viewModelFactory)
}