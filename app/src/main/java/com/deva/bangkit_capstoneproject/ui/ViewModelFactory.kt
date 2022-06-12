package com.deva.bangkit_capstoneproject.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.deva.bangkit_capstoneproject.core.ChatRepository
import com.deva.bangkit_capstoneproject.ui.chat.ChatViewModel
import com.deva.bangkit_capstoneproject.ui.main.MainViewModel


class ViewModelFactory(private val chatRepository: ChatRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(chatRepository) as T
            }
            modelClass.isAssignableFrom(ChatViewModel::class.java) -> {
                ChatViewModel(chatRepository) as T
            }
            else -> throw  IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}