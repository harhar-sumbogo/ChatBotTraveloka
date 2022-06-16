package com.traveloka.chatbot.ui.chat

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.traveloka.chatbot.core.data.Result
import com.traveloka.chatbot.core.domain.model.MessageModel
import com.traveloka.chatbot.core.domain.usecase.ChatUseCase

class ChatViewModel(private val chatUseCase: ChatUseCase) : ViewModel() {
    fun sendMessage(messageModel: MessageModel, token: String): LiveData<Result<MessageModel>> {
        return chatUseCase.sendMessage(messageModel, token)
    }

    fun loadAllMessage() = chatUseCase.loadCacheMessage()
}