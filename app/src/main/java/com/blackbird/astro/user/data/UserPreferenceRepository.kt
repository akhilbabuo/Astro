package com.blackbird.astro.user.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.blackbird.astro.extenstions.get
import javax.inject.Inject

class UserPreferenceRepository @Inject constructor(private val userPref: DataStore<Preferences>) {

    companion object{
        val userNameKey = stringPreferencesKey("user_name")
        val userSpendLimit = stringPreferencesKey("user_spend_limit")

    }

    suspend fun setUserName(userName:String){
            userPref.edit { pref->
                pref[userNameKey] = userName
            }
    }

    suspend fun getUserName(): String? {
        return userPref.get(userNameKey)
    }



    suspend fun setSpendLimit(limit:Float){
        userPref.edit {
            it[userSpendLimit] = limit.toString()
        }
    }

    suspend fun getSpendLimit():Float?{
        return userPref.get(userSpendLimit)?.toFloatOrNull()
    }



}