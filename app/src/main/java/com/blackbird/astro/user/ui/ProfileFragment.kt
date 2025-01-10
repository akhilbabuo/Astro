package com.blackbird.astro.user.ui

import com.blackbird.astro.R
import com.blackbird.astro.core.ui.BaseViewModelFragment
import com.blackbird.astro.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProfileFragment:BaseViewModelFragment<FragmentProfileBinding,ProfileViewModel>(R.layout.fragment_profile,ProfileViewModel::class) {




}