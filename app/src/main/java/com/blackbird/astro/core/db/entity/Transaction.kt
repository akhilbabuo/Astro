package com.blackbird.astro.core.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("user_transaction")
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val amount: Float,
    val method: String,
    val time: Int
)
