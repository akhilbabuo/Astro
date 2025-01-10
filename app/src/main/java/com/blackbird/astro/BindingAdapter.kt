package com.blackbird.astro

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("isVisible")
fun View.isVisible(value: Boolean) {
    visibility = if (value) {
        View.VISIBLE
    } else {
        View.GONE
    }
}