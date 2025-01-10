package com.blackbird.astro.core.models

data class Transaction(
    val amount: Float,
    val method: String,
    val time: Int
)
