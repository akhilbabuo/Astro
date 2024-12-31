package com.blackbird.astro.base.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment


abstract class BaseFragment<VB : ViewDataBinding>(@LayoutRes val layout: Int) : Fragment() {

    open var binding: VB? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, layout, container, false)
        binding?.lifecycleOwner = viewLifecycleOwner
        return binding?.root ?: inflater.inflate(layout, container, false)
    }

}