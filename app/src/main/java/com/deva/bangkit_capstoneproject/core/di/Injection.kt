package com.deva.bangkit_capstoneproject.core.di

import android.content.Context
import com.deva.bangkit_capstoneproject.core.ChatRepository
import com.deva.bangkit_capstoneproject.core.data.local.room.ChatDatabase
import com.deva.bangkit_capstoneproject.core.data.remote.api.ApiConfig
import com.deva.bangkit_capstoneproject.core.domain.repository.IChatRepository
import com.deva.bangkit_capstoneproject.core.domain.usecase.ChatInteractor
import com.deva.bangkit_capstoneproject.core.domain.usecase.ChatUseCase
import com.deva.bangkit_capstoneproject.core.utils.AppExecutors

object Injection {
    private fun provideRepository(context: Context) : IChatRepository {
        val apiService = ApiConfig.getApiService()
        val database = ChatDatabase.getInstance(context)
        val appExecutors = AppExecutors()

        return ChatRepository.getInstance(database, apiService, appExecutors)
    }

    fun provideChatUseCase(context: Context): ChatUseCase {
        val repository = provideRepository(context)
        return ChatInteractor(repository)
    }
}