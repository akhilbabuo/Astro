package com.blackbird.astro.extenstions

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

fun <T> Fragment.collectLatestStateFlow(flow: StateFlow<T>, collect: suspend (T) -> Unit) {
    lifecycleScope.launch {
        viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            flow.collectLatest {
                collect.invoke(it)
            }
        }
    }
}

fun <T> AppCompatActivity.collectStateFlow(flow: StateFlow<T>, collect : suspend (T)->Unit){
    lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.INITIALIZED){
            flow.collectLatest {
                collect.invoke(it)
            }
        }
    }
}