package com.traveloka.chatbot.core.di

import android.content.Context
import com.traveloka.chatbot.core.ChatRepository
import com.traveloka.chatbot.core.data.local.room.ChatDatabase
import com.traveloka.chatbot.core.data.remote.api.ApiConfig
import com.traveloka.chatbot.core.domain.repository.IChatRepository
import com.traveloka.chatbot.core.domain.usecase.ChatInteractor
import com.traveloka.chatbot.core.domain.usecase.ChatUseCase
import com.traveloka.chatbot.core.utils.AppExecutors

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