package com.traveloka.chatbot.core.data.remote.api

import com.traveloka.chatbot.core.data.remote.request.MessageRequest
import com.traveloka.chatbot.core.data.remote.response.GroupCreationResponse
import com.traveloka.chatbot.core.data.remote.response.MessageResponse
import com.traveloka.chatbot.core.data.remote.response.UserCreationResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    @POST("api/chat/user")
    fun createUser(
        @Header("Authorization")
        token: String
    ): Call<UserCreationResponse>

    @POST("api/chat/group")
    fun createGroup(
        @Header("Authorization")
        token: String
    ): Call<GroupCreationResponse>

    @POST("api/chat/message")
    fun sendMessage(
        @Header("Authorization")
        token: String,

        @Body
        payload: MessageRequest
    ): Call<MessageResponse>
}