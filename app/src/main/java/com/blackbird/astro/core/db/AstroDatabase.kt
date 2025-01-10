package com.blackbird.astro.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.blackbird.astro.core.db.entity.Transaction


@Database(
    entities = [Transaction::class],
    version = 1,
    exportSchema = false
)
abstract class AstroDatabase : RoomDatabase() {

    abstract val transactionDao: TransactionDao
}