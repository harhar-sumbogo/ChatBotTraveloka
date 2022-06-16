package com.deva.bangkit_capstoneproject.core.domain.usecase

import androidx.lifecycle.LiveData
import com.deva.bangkit_capstoneproject.core.ChatRepository
import com.deva.bangkit_capstoneproject.core.data.Result
import com.deva.bangkit_capstoneproject.core.domain.model.MessageModel
import com.deva.bangkit_capstoneproject.core.domain.repository.IChatRepository

class ChatInteractor(private val chatRepository: IChatRepository): ChatUseCase {
    override fun createUser(token: String): LiveData<String> = chatRepository.createUser(token)

    override fun createGroup(token: String): LiveData<Result<String>> = chatRepository
        .createGroup(token)

    override fun sendMessage(message: MessageModel, token: String): LiveData<Result<MessageModel>> =
        chatRepository.sendMessage(message, token)

    override fun loadCacheMessage(): LiveData<List<MessageModel>> = chatRepository.loadCacheMessage()

    override fun deleteCacheChat() = chatRepository.deleteCacheChat()
}