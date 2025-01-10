package com.blackbird.astro.core.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.blackbird.astro.core.db.entity.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(transaction: Transaction)

    @Update
    fun updateTransaction(transaction: Transaction)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(transactions: List<Transaction>)

    @Delete
    fun deleteTransaction(transaction: Transaction)

    @Query("SELECT * FROM  user_transaction")
    fun getAllTransaction(): Flow<List<Transaction>>

}