package com.deva.bangkit_capstoneproject.core.di

import android.content.Context
import com.deva.bangkit_capstoneproject.core.ChatRepository
import com.deva.bangkit_capstoneproject.core.data.local.room.ChatDatabase
import com.deva.bangkit_capstoneproject.core.data.remote.api.ApiConfig
import com.deva.bangkit_capstoneproject.core.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context) : ChatRepository {
        val apiService = ApiConfig.getApiService()
        val database = ChatDatabase.getInstance(context)
        val appExecutors = AppExecutors()

        return ChatRepository.getInstance(database, apiService, appExecutors)
    }
}