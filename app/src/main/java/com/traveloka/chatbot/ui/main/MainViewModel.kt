package com.traveloka.chatbot.ui.main

import androidx.lifecycle.ViewModel
import com.traveloka.chatbot.core.domain.usecase.ChatUseCase

class MainViewModel (private val chatUseCase: ChatUseCase): ViewModel() {
    fun createUser(token: String) = chatUseCase.createUser(token)

    fun createGroup(token: String) = chatUseCase.createGroup(token)

    fun deleteCacheChat() = chatUseCase.deleteCacheChat()
}