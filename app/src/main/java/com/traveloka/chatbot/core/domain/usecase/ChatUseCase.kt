package com.traveloka.chatbot.core.domain.usecase

import androidx.lifecycle.LiveData
import com.traveloka.chatbot.core.data.Result
import com.traveloka.chatbot.core.domain.model.MessageModel

interface ChatUseCase {
    fun createUser(token: String): LiveData<String>

    fun createGroup(token: String): LiveData<Result<String>>

    fun sendMessage(message: MessageModel, token: String): LiveData<Result<MessageModel>>

    fun loadCacheMessage(): LiveData<List<MessageModel>>

    fun deleteCacheChat()
}