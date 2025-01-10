package com.blackbird.astro.home.ui

import android.text.InputType
import android.util.Log
import androidx.navigation.fragment.findNavController
import com.blackbird.astro.R
import com.blackbird.astro.core.ui.BaseViewModelFragment
import com.blackbird.astro.core.ui.QueryBottomSheetDialogFragment
import com.blackbird.astro.databinding.FragmentHomeBinding
import com.blackbird.astro.extenstions.collectLatestStateFlow
import com.blackbird.astro.transactions.TransactionItemAdapter
import com.blackbird.astro.transactions.ui.AddTransactionBottomSheet
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseViewModelFragment<FragmentHomeBinding, HomeViewModel>(
    R.layout.fragment_home,
    HomeViewModel::class
) {


    override fun initView() {
        fragmentViewModel.initializeData()
        initializeRecyclerview()
    }

    private fun initializeRecyclerview() {
        binding?.rvTransaction?.let {
            val transactionItemAdapter = TransactionItemAdapter()
            it.adapter = transactionItemAdapter
            collectLatestStateFlow(fragmentViewModel.transactionList) { data ->
                transactionItemAdapter.setTransactionList(
                    if (data.size > 5) data.subList(
                        0,
                        5
                    ) else data
                )
            }
        }
    }


    override fun FragmentHomeBinding.setClickListeners() {
        fab.setOnClickListener {
            showAddTransaction()
//            fragmentViewModel.addTransaction()
        }
        userProfileview.setOnClickListener {
            openProfile()
        }
        tvLimit.setOnClickListener {
            limitClick()
        }
        tvTitleLastTrans.setOnClickListener {
            openTransactionList()
        }
    }

    private fun openTransactionList() {
        findNavController().navigate(R.id.action_homeFragment_to_transactionsFragment)
    }

    private fun limitClick() {
        if (fragmentViewModel.spendLimit.value == null) {
            val sheet = QueryBottomSheetDialogFragment(
                "set Spending Limit",
                "Setting spending Limit will help you manage better",
                InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL,
                hint = "Limit"
            ) { limit ->
                limit.toFloatOrNull()?.let {
                    fragmentViewModel.setSpendingLimit(it)
                }
            }
            sheet.show(childFragmentManager, QueryBottomSheetDialogFragment::class.simpleName)
        }
    }

    private fun showAddTransaction() {
        val sheet = AddTransactionBottomSheet()
        sheet.show(childFragmentManager, AddTransactionBottomSheet::class.simpleName)
    }

    private fun openProfile() {
        findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
    }

}