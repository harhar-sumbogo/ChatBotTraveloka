package com.deva.bangkit_capstoneproject.ui.main

import androidx.lifecycle.ViewModel
import com.deva.bangkit_capstoneproject.core.ChatRepository
import com.deva.bangkit_capstoneproject.core.domain.usecase.ChatUseCase

class MainViewModel (private val chatUseCase: ChatUseCase): ViewModel() {
    fun createUser(token: String) = chatUseCase.createUser(token)

    fun createGroup(token: String) = chatUseCase.createGroup(token)

    fun deleteCacheChat() = chatUseCase.deleteCacheChat()
}