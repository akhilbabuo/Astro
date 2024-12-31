package com.blackbird.astro.base.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.blackbird.astro.BR
import kotlin.reflect.KClass


abstract class BaseViewModelFragment<VM : ViewModel, VB : ViewDataBinding>(
    @LayoutRes val mLayout: Int, private val viewModelClass: KClass<VM>
) : BaseFragment<VB>(mLayout) {

    open lateinit var fragmentViewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        fragmentViewModel = ViewModelProvider(this)[viewModelClass.java]
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    /**
    Set variable programmatically always name viewmodel in layout as "viewModel"
    https://stackoverflow.com/a/57763743/7968986
     **/

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.setVariable(BR.viewModel, fragmentViewModel)
        initView()
        handleObservers()
    }

    open fun initView() {}


    open fun handleObservers() {}
}