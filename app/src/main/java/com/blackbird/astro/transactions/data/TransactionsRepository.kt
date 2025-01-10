package com.blackbird.astro.transactions.data

import com.blackbird.astro.core.db.TransactionDao
import com.blackbird.astro.core.db.entity.Transaction
import com.blackbird.astro.transactions.ui.Response
import kotlinx.coroutines.flow.Flow

import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TransactionsRepository @Inject constructor(
    private val transactionDao: TransactionDao
) {
    suspend fun getTransactions(limit: TransactionLimit = TransactionLimit.Week(1)): Flow<Response<List<Transaction>>> {
        return flow {
            transactionDao.getAllTransaction().catch { throwable->
                emit(listOf())
            }.collect {
                emit(Response.SUCCESS(it))
            }

        }
    }

    suspend fun insertTransaction(transaction: Transaction){
        transactionDao.insert(transaction)
    }

}

sealed class TransactionLimit {
    class Week(val count: Int) : TransactionLimit()
    class Days(val count: Int) : TransactionLimit()
    class Months(val count: Int) : TransactionLimit()
    class Years(val count: Int) : TransactionLimit()
    data object All : TransactionLimit()

}