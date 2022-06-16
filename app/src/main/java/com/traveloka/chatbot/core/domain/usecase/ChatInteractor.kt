package com.traveloka.chatbot.core.domain.usecase

import androidx.lifecycle.LiveData
import com.traveloka.chatbot.core.data.Result
import com.traveloka.chatbot.core.domain.model.MessageModel
import com.traveloka.chatbot.core.domain.repository.IChatRepository

class ChatInteractor(private val chatRepository: IChatRepository): ChatUseCase {
    override fun createUser(token: String): LiveData<String> = chatRepository.createUser(token)

    override fun createGroup(token: String): LiveData<Result<String>> = chatRepository
        .createGroup(token)

    override fun sendMessage(message: MessageModel, token: String): LiveData<Result<MessageModel>> =
        chatRepository.sendMessage(message, token)

    override fun loadCacheMessage(): LiveData<List<MessageModel>> = chatRepository.loadCacheMessage()

    override fun deleteCacheChat() = chatRepository.deleteCacheChat()
}