package com.deva.bangkit_capstoneproject.ui.main

import androidx.lifecycle.ViewModel
import com.deva.bangkit_capstoneproject.core.ChatRepository

class MainViewModel (private val chatRepository: ChatRepository): ViewModel() {
    fun saveToken(token: String) = chatRepository.saveToken(token)
}