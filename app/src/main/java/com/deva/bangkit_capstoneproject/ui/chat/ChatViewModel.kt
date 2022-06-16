package com.deva.bangkit_capstoneproject.ui.chat

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.deva.bangkit_capstoneproject.core.ChatRepository
import com.deva.bangkit_capstoneproject.core.data.Result
import com.deva.bangkit_capstoneproject.core.domain.model.MessageModel
import com.deva.bangkit_capstoneproject.core.domain.usecase.ChatUseCase

class ChatViewModel(private val chatUseCase: ChatUseCase) : ViewModel() {
    fun sendMessage(messageModel: MessageModel, token: String): LiveData<Result<MessageModel>> {
        return chatUseCase.sendMessage(messageModel, token)
    }

    fun loadAllMessage() = chatUseCase.loadCacheMessage()
}