package com.blackbird.astro.user.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blackbird.astro.user.data.UserEntryRepository
import com.blackbird.astro.user.data.UserPreferenceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UserEntryViewModel @Inject constructor(
    private val userEntryRepository: UserEntryRepository,
    private val userPreferenceRepository: UserPreferenceRepository
) :
    ViewModel() {

    private val _userNameSaveStatus = MutableLiveData<Boolean>()
    val userNameSaveStatus get() = _userNameSaveStatus


    fun saveUserName(userName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            userPreferenceRepository.setUserName(userName)
            _userNameSaveStatus.postValue(true)
        }
    }

}