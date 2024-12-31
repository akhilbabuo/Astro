package com.blackbird.astro.user.ui

import android.util.Log
import androidx.navigation.fragment.findNavController
import com.blackbird.astro.base.ui.BaseViewModelFragment
import com.blackbird.astro.R
import com.blackbird.astro.databinding.FragmentUserEntryBinding
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class UserEntryFragment : BaseViewModelFragment<UserEntryViewModel, FragmentUserEntryBinding>(
    R.layout.fragment_user_entry,
    UserEntryViewModel::class
) {

    override fun initView() {
        binding?.apply {
            btnQuUserName.setOnClickListener {
                fragmentViewModel.saveUserName(etQuUserName.text.toString())
            }
        }
        FirebaseMessaging.getInstance().token.addOnSuccessListener {
            Log.d("akhil", "onNewToken - frg: $it ")
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