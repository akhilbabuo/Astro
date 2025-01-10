package com.blackbird.astro.transactions.data

import com.blackbird.astro.core.models.Transaction
import com.blackbird.astro.transactions.ui.Response
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class TransactionsRepository @Inject constructor() {

    fun getTransactions(limit: TransactionLimit = TransactionLimit.Week(1)): StateFlow<Response<List<Transaction>>> {
        return MutableStateFlow(
            Response.SUCCESS(
                listOf<Transaction>(
                    Transaction(
                        20f,
                        "upi",
                        1221
                    )
                )
            )
        )
    }


}

sealed class TransactionLimit {
    class Week(val count: Int) : TransactionLimit()
    class Days(val count: Int) : TransactionLimit()
    class Months(val count: Int) : TransactionLimit()
    class Years(val count: Int) : TransactionLimit()
    data object All : TransactionLimit()

}