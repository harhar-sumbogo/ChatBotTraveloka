package com.deva.bangkit_capstoneproject.ui.chat

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.deva.bangkit_capstoneproject.core.ChatRepository
import com.deva.bangkit_capstoneproject.core.data.Result
import com.deva.bangkit_capstoneproject.core.data.remote.response.Payload
import com.deva.bangkit_capstoneproject.core.domain.model.MessageModel
import com.deva.bangkit_capstoneproject.core.utils.DataMapper

class ChatViewModel(private val chatRepository: ChatRepository): ViewModel() {
    fun sendMessage(messageModel: MessageModel): LiveData<Result<MessageModel>> {
        return chatRepository.sendMessage(messageModel)
    }

    fun loadAllMessage() = chatRepository.loadAllMessage()
}