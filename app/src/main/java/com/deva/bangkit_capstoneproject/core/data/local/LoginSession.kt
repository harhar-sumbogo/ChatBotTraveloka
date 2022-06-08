package com.deva.bangkit_capstoneproject.core.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class LoginSession private constructor(private val dataStore: DataStore<Preferences>) {
    private val tokenKey = stringPreferencesKey("session_token")

    fun getToken(): Flow<String> {
        return dataStore.data.map {
            it[tokenKey] ?: ""
        }
    }

    suspend fun saveToken(token: String) {
        dataStore.edit {
            it[tokenKey] = token
        }
    }

    suspend fun clearSession() {
        dataStore.edit {
            it.clear()
        }
    }

    companion object{
        @Volatile
        private var INSTANCE: LoginSession? = null

        fun getInstance(dataStore: DataStore<Preferences>): LoginSession {
            return INSTANCE ?: synchronized(this) {
                val instance = LoginSession(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}