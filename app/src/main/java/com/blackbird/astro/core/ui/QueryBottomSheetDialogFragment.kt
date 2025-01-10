package com.blackbird.astro.core.ui

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.blackbird.astro.databinding.QueryBottomSheetDialogBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class QueryBottomSheetDialogFragment(
    private val title: String? = null,
    private val info: String? = null,
    private val inputType: Int? = null,
    private val hint: String? = null,
    private val positiveCtaText :String = "ok",
    private val onClick: (String) -> Unit
) : BottomSheetDialogFragment() {

    lateinit var binding: QueryBottomSheetDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = QueryBottomSheetDialogBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
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
        binding.tvTitle.text = title
        binding.info.text = info
        inputType?.let {
            binding.etUserEntry.inputType = it
        }
        binding.etUserEntry.hint = hint
        binding.btPositiveCta.text = positiveCtaText
        binding.btPositiveCta.setOnClickListener {
            onClick.invoke(binding.etUserEntry.text.toString())
            dismiss()
        }
    }
}