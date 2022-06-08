package com.deva.bangkit_capstoneproject.core.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.deva.bangkit_capstoneproject.core.ChatRepository
import com.deva.bangkit_capstoneproject.core.data.local.LoginSession

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "login_session")
object Injection {
    fun provideRepository(context: Context) : ChatRepository {
        val loginSession = LoginSession.getInstance(context.dataStore)

        return ChatRepository.getInstance(loginSession)
    }
}