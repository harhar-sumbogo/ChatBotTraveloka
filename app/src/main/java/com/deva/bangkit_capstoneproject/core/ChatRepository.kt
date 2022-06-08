package com.deva.bangkit_capstoneproject.core

import com.deva.bangkit_capstoneproject.core.data.local.LoginSession
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class ChatRepository private constructor(
    private val loginSession: LoginSession
) {
//    akan dioptimalkan
    fun getToken(): Flow<String> {
        return loginSession.getToken()
    }

    fun saveToken(token: String) {
        CoroutineScope(Dispatchers.IO).launch {
            loginSession.saveToken(token)
        }
    }

    companion object {
        @Volatile
        private var instance: ChatRepository? = null
        fun getInstance(
            loginSession: LoginSession
        ): ChatRepository =
            instance ?: synchronized(this) {
                instance ?: ChatRepository(loginSession)
            }.also { instance = it }
    }
}