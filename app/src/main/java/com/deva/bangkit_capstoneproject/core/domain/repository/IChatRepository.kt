package com.deva.bangkit_capstoneproject.core.domain.repository

import androidx.lifecycle.LiveData
import com.deva.bangkit_capstoneproject.core.data.Result
import com.deva.bangkit_capstoneproject.core.domain.model.MessageModel

interface IChatRepository {
    fun createUser(token: String): LiveData<String>

    fun createGroup(token: String): LiveData<Result<String>>

    fun sendMessage(message: MessageModel, token: String): LiveData<Result<MessageModel>>

    fun loadCacheMessage(): LiveData<List<MessageModel>>

    fun deleteCacheChat()
}