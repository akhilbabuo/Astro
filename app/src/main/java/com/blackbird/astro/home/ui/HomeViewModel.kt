package com.blackbird.astro.home.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blackbird.astro.R
import com.blackbird.astro.core.ResourcesProvider
import com.blackbird.astro.core.db.entity.Transaction
import com.blackbird.astro.transactions.data.TransactionLimit
import com.blackbird.astro.transactions.data.TransactionsRepository
import com.blackbird.astro.transactions.ui.Response
import com.blackbird.astro.user.data.UserPreferenceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.absoluteValue

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userPref: UserPreferenceRepository,
    private val transactionRepository: TransactionsRepository,
    private val resourcesProvider: ResourcesProvider
) : ViewModel() {


    private val _userName = MutableLiveData<String>("")
    val userName: LiveData<String> get() = _userName

    private val _transactionsList = MutableStateFlow<List<Transaction>>(listOf())
    val transactionList: StateFlow<List<Transaction>> get() = _transactionsList

    private val _weeklySpending = MutableStateFlow<Float>(0f)
    val weeklySpending: StateFlow<Float> get() = _weeklySpending

    val isErrorVisible = MutableStateFlow(false)
    val errorMessage = MutableStateFlow<String>("")

    val spendingLimitText = MutableStateFlow<String>(resourcesProvider.getString(R.string.add_spending_limit))
    val spendLimit = MutableStateFlow<Float?>(null)


    fun initializeData() {
        getUsername()
        getTransactions()
    }

    private fun getSpendingLimit() {
        viewModelScope.launch {
            userPref.getSpendLimit().let { limit->
                when(limit){
                    null -> {
                        resourcesProvider.getString(R.string.add_spending_limit)
                    }
                    else -> {
                        spendLimit.emit(limit)
                        val diff = limit - weeklySpending.value
                        if (diff>0) "safe to spend $diff" else "overspend ${diff.absoluteValue}"
                    }
                }.let { text ->
                    spendingLimitText.emit(text)
                }
            }
        }
    }

    private fun getTransactions() {
        viewModelScope.launch(Dispatchers.IO) {
            transactionRepository.getTransactions(TransactionLimit.Week(1))
                .collectLatest { response ->
                    when (response) {
                        is Response.SUCCESS -> {
                            val data = response.data ?: listOf()
                            _transactionsList.emit(data)
                            data.map { it.amount }.sum().let { _weeklySpending.emit(it) }
                            getSpendingLimit()
                        }

                        is Response.ERROR -> {
                            isErrorVisible.emit(true)
                        }
                    }

                }
        }
    }

    private fun getUsername() {
        viewModelScope.launch {
            userPref.getUserName()?.let {
                _userName.postValue(it)
            }
        }
    }

    fun setSpendingLimit(float: Float) {
        viewModelScope.launch {
            userPref.setSpendLimit(float)
            getSpendingLimit()
        }
    }


}