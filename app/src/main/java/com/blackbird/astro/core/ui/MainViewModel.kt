package com.blackbird.astro.core.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blackbird.astro.user.data.UserPreferenceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val userPreferenceRepository: UserPreferenceRepository):ViewModel() {


    fun getUserStatus(): MutableSharedFlow<String?> {
        val userNameFLow = MutableSharedFlow<String?>()
        viewModelScope.launch {
                userNameFLow.emit(userPreferenceRepository.getUserName())
        }
        return userNameFLow
    }
}