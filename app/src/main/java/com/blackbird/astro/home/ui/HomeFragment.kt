package com.blackbird.astro.home.ui

import com.blackbird.astro.R
import com.blackbird.astro.base.ui.BaseViewModelFragment
import com.blackbird.astro.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseViewModelFragment<HomeViewModel, FragmentHomeBinding>(
    R.layout.fragment_home,
    HomeViewModel::class
) {


    override fun initView() {
        binding?.apply {


        }

    }

}