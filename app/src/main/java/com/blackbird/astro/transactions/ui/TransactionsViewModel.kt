package com.blackbird.astro.transactions.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blackbird.astro.core.models.Transaction
import com.blackbird.astro.transactions.data.TransactionsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionsViewModel @Inject constructor(private val repository: TransactionsRepository) : ViewModel() {

    val transactionListFlow = MutableStateFlow(listOf<Transaction>())

    fun getTransactions() {
        viewModelScope.launch(Dispatchers.IO) {
            val transactionListResponse : StateFlow<Response<List<Transaction>>> = repository.getTransactions()
            when(transactionListResponse.value){
                is Response.SUCCESS ->{
                    println("success")
                    transactionListResponse.value.data?.let { transactionListFlow.emit(it) }
                }
                is Response.ERROR ->{

                }
            }
        }
    }


}


sealed class Response<T>(
    val data: T? = null,
    val message : String? = null
) {
    class SUCCESS<T>(data: T) : Response<T>(data = data)
    class ERROR<T>(errorMessage : String,data: T? = null) : Response<T>(message = errorMessage,)
}