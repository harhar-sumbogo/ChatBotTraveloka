package com.deva.bangkit_capstoneproject.ui.main

import androidx.lifecycle.ViewModel
import com.deva.bangkit_capstoneproject.core.ChatRepository

class MainViewModel (private val chatRepository: ChatRepository): ViewModel() {
    fun createUser(token: String) = chatRepository.createUser(token)

    fun createGroup() = chatRepository.createGroup()

    fun logout() = chatRepository.logout()
}