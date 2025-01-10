package com.blackbird.astro.transactions.ui

import android.util.Log
import com.blackbird.astro.R
import com.blackbird.astro.core.ui.BaseViewModelFragment
import com.blackbird.astro.databinding.FragmentTransactionsBinding
import com.blackbird.astro.extenstions.collectLatestStateFlow
import com.blackbird.astro.transactions.TransactionItemAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TransactionsFragment :
    BaseViewModelFragment<FragmentTransactionsBinding, TransactionsViewModel>(
        R.layout.fragment_transactions,
        TransactionsViewModel::class
    ) {


    override fun initView() {
        super.initView()
        fragmentViewModel.getTransactions()
        initRecycleView()
    }


    private fun initRecycleView() {
        binding?.rvTransaction?.apply {
            val mAdapter = TransactionItemAdapter()
            adapter = mAdapter
            collectLatestStateFlow(fragmentViewModel.transactionListFlow) { transactionList ->
                Log.d(this::class.simpleName, "initRecycleView: ${transactionList.size}")
                if (transactionList.isEmpty()) {
                    showErrorScreen("No Data")
                } else {
                    mAdapter.setTransactionList(transactionList)
                }
            }

        }
    }


    private fun showErrorScreen(errorMessage : String? = "error"){
        binding?.apply {
            isErrorVisible = true
            includeEmptyScreen.tvErrorMessage.text = errorMessage
        }
    }
}
