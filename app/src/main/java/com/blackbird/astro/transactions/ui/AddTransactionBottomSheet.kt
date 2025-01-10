package com.blackbird.astro.transactions.ui

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.blackbird.astro.core.db.entity.Transaction
import com.blackbird.astro.databinding.AddTransactionBottomSheetLayoutBinding
import com.blackbird.astro.extenstions.TAG
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AddTransactionBottomSheet : BottomSheetDialogFragment() {

    lateinit var binding: AddTransactionBottomSheetLayoutBinding

    val transactionsViewModel by viewModels<TransactionsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d(TAG, "onCreateView: ")
        binding = AddTransactionBottomSheetLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        Log.d(TAG, "onCreateDialog: ")
        dialog?.setOnShowListener { it ->
            val d = it as BottomSheetDialog
            val bottomSheet =
                d.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            bottomSheet?.let {
                val behavior = BottomSheetBehavior.from(it)
                behavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
        return super.onCreateDialog(savedInstanceState)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated: ")
        binding.btPositiveCta.setOnClickListener {
            transactionsViewModel.addTransaction(Transaction(
                amount = binding.etAmount.text.toString().toFloatOrNull() ?: 0f,
                method = binding.etMethod.text.toString(),
                time = binding.etdate.text.toString().toIntOrNull() ?: 0
            ))
            dismissAllowingStateLoss()

        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        Log.d(TAG, "onDismiss: ")
    }




}