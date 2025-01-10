package com.blackbird.astro.user.ui

import androidx.navigation.fragment.findNavController
import com.blackbird.astro.core.ui.BaseViewModelFragment
import com.blackbird.astro.R
import com.blackbird.astro.databinding.FragmentUserEntryBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class UserEntryFragment : BaseViewModelFragment<FragmentUserEntryBinding,UserEntryViewModel>(
    R.layout.fragment_user_entry,
    UserEntryViewModel::class
) {

    override fun initView() {
        binding?.apply {
            btnQuUserName.setOnClickListener {
                fragmentViewModel.saveUserName(etQuUserName.text.toString())
            }
        }

    }

    override fun handleObservers() {
        fragmentViewModel.userNameSaveStatus.observe(viewLifecycleOwner) { status ->
            if (status)
                navigateToHome()
        }
    }


    private fun navigateToHome() {
        findNavController().navigate(R.id.action_userEntry_to_homeFragment)
    }

}