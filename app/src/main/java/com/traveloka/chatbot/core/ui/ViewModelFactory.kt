package com.traveloka.chatbot.core.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.traveloka.chatbot.core.di.Injection
import com.traveloka.chatbot.core.domain.usecase.ChatUseCase
import com.traveloka.chatbot.ui.chat.ChatViewModel
import com.traveloka.chatbot.ui.main.MainViewModel


class ViewModelFactory private constructor(private val chatUseCase: ChatUseCase) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(
                    Injection.provideChatUseCase(context)
                )
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(chatUseCase) as T
            }
            modelClass.isAssignableFrom(ChatViewModel::class.java) -> {
                ChatViewModel(chatUseCase) as T
            }
            else -> throw  IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}