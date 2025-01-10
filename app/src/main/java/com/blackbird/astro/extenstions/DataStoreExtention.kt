package com.blackbird.astro.extenstions

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import java.io.IOException


const val TAG = "pref"
/**
 * handles IO exception and return flow pref
 */
fun DataStore<Preferences>.getPref(): Flow<Preferences> {
    return data.catch { exception ->
        if (exception is IOException) {
            Log.e(TAG, "getPref: ", )
            emit(emptyPreferences())
        } else {
            throw exception
        }
    }
}

suspend fun DataStore<Preferences>.get(key: Preferences.Key<String>): String? {
    return getPref().map { it[key] }.firstOrNull()
}