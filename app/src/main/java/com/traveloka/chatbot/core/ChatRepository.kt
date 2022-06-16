package com.traveloka.chatbot.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.traveloka.chatbot.core.data.Result
import com.traveloka.chatbot.core.data.local.room.ChatDatabase
import com.traveloka.chatbot.core.data.remote.api.ApiService
import com.traveloka.chatbot.core.data.remote.response.GroupCreationResponse
import com.traveloka.chatbot.core.data.remote.response.MessageResponse
import com.traveloka.chatbot.core.data.remote.response.UserCreationResponse
import com.traveloka.chatbot.core.domain.model.MessageModel
import com.traveloka.chatbot.core.domain.repository.IChatRepository
import com.traveloka.chatbot.core.utils.AppExecutors
import com.traveloka.chatbot.core.utils.DataMapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChatRepository private constructor(
    private val database: ChatDatabase,
    private val apiService: ApiService,
    private val appExecutors: AppExecutors
): IChatRepository {
    override fun createUser(token: String): LiveData<String> {
        val result = MutableLiveData<String>()

        val service = apiService.createUser("Bearer $token")
        service.enqueue(object : Callback<UserCreationResponse> {
            override fun onResponse(
                call: Call<UserCreationResponse>,
                response: Response<UserCreationResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        result.value = responseBody.message
                    }
                } else {
                    result.value = "Error"
                }
            }

            override fun onFailure(call: Call<UserCreationResponse>, t: Throwable) {
                result.value = "Error"
            }
        })

        return result
    }

    override fun createGroup(token: String): LiveData<Result<String>> {
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
                } else {
                    result.value = Result.Error("Error")
                }
            }

            override fun onFailure(call: Call<GroupCreationResponse>, t: Throwable) {
                result.value = Result.Error("Error")
            }
        })

        return result
    }

    override fun loadCacheMessage(): LiveData<List<MessageModel>> {
        return Transformations.map(database.chatDao().getAllChat()) {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun deleteCacheChat() {
        appExecutors.diskIO().execute {
            database.chatDao().resetChat()
        }
    }

    override fun sendMessage(message: MessageModel, token: String): LiveData<Result<MessageModel>> {
        appExecutors.diskIO().execute {
            database.chatDao().insertChat(DataMapper.modelToEntity(message))
        }

        val result = MutableLiveData<Result<MessageModel>>()
        val messageRequest = DataMapper.modelToRequest(message)

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
                        result.value = Result.Success(
                            DataMapper.responseToModel(
                                responseBody.payload
                            )
                        )
                        appExecutors.diskIO().execute {
                            database.chatDao().insertChat(
                                DataMapper.responseToEntity(
                                    responseBody.payload
                                )
                            )
                        }
                    }
                } else {
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
            database: ChatDatabase,
            apiService: ApiService,
            appExecutors: AppExecutors
        ): ChatRepository =
            instance ?: synchronized(this) {
                instance ?: ChatRepository(database, apiService, appExecutors)
            }.also { instance = it }
    }
}