package com.deva.bangkit_capstoneproject.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.deva.bangkit_capstoneproject.core.data.Result
import com.deva.bangkit_capstoneproject.core.data.local.LoginSession
import com.deva.bangkit_capstoneproject.core.data.remote.api.ApiService
import com.deva.bangkit_capstoneproject.core.data.remote.request.MessageRequest
import com.deva.bangkit_capstoneproject.core.data.remote.response.GroupCreationResponse
import com.deva.bangkit_capstoneproject.core.data.remote.response.MessageResponse
import com.deva.bangkit_capstoneproject.core.data.remote.response.Payload
import com.deva.bangkit_capstoneproject.core.data.remote.response.UserCreationResponse
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChatRepository private constructor(
    private val loginSession: LoginSession,
    private val apiService: ApiService
) {
    private var token: String? = null

    fun createUser(token: String): LiveData<String> {
        val result = MutableLiveData<String>()

        this.token = token
        val service = apiService.createUser("Bearer $token")
        service.enqueue(object: Callback<UserCreationResponse> {
            override fun onResponse(
                call: Call<UserCreationResponse>,
                response: Response<UserCreationResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        result.value = responseBody.message
                    }
                }else {
                    result.value = "Error"
                }
            }

            override fun onFailure(call: Call<UserCreationResponse>, t: Throwable) {
                result.value = "Error"
            }
        })

        return result
    }

    fun createGroup(): LiveData<Result<String>> {
        val result = MutableLiveData<Result<String>>()

        result.value = Result.Loading
        val service = apiService.createGroup("Bearer $token")
        service.enqueue(object : Callback<GroupCreationResponse> {
            override fun onResponse(
                call: Call<GroupCreationResponse>,
                response: Response<GroupCreationResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        result.value = Result.Success(responseBody.message)
                    }
                }else {
                    result.value = Result.Error("Error")
                }
            }

            override fun onFailure(call: Call<GroupCreationResponse>, t: Throwable) {
                result.value = Result.Error("Error")
            }
        })

        return result
    }

    fun sendMessage(message: String, tag: String? = null): LiveData<Result<Payload>> {
        val messageRequest = MessageRequest(
            message, tag
        )

        val result = MutableLiveData<Result<Payload>>()

        result.value = Result.Loading
        val service = apiService.sendMessage("Bearer $token", messageRequest)
        service.enqueue(object : Callback<MessageResponse> {
            override fun onResponse(
                call: Call<MessageResponse>,
                response: Response<MessageResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        result.value = Result.Success(responseBody.payload)
                    }
                }else {
                    result.value = Result.Error("Error")
                }
            }

            override fun onFailure(call: Call<MessageResponse>, t: Throwable) {
                result.value = Result.Error("Error")
            }
        })
        return result
    }

    companion object {
        @Volatile
        private var instance: ChatRepository? = null
        fun getInstance(
            loginSession: LoginSession,
            apiService: ApiService
        ): ChatRepository =
            instance ?: synchronized(this) {
                instance ?: ChatRepository(loginSession,apiService)
            }.also { instance = it }
    }
}