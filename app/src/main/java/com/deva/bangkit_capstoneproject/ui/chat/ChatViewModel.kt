package com.deva.bangkit_capstoneproject.ui.chat

import androidx.lifecycle.ViewModel
import com.deva.bangkit_capstoneproject.core.ChatRepository

class ChatViewModel(private val chatRepository: ChatRepository): ViewModel() {
    fun sendMessage(message: String, tag: String? = null) = chatRepository.sendMessage(message, tag)
}