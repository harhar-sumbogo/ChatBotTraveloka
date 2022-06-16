package com.deva.bangkit_capstoneproject.core.utils

import android.util.Log
import android.util.Patterns
import com.deva.bangkit_capstoneproject.core.data.local.entity.MessageEntity
import com.deva.bangkit_capstoneproject.core.data.remote.request.MessageRequest
import com.deva.bangkit_capstoneproject.core.data.remote.response.Payload
import com.deva.bangkit_capstoneproject.core.domain.model.MessageModel

object DataMapper {
    private val responseTagList = arrayOf(
        "resto_recommendation_response",
        "hotel_recommendation_response"
    )

    fun responseToModel(payload: Payload, user: String = "BOT"): MessageModel {
        var message = payload.message

        if (payload.tag in responseTagList) {
            val temp = payload.message.split(":")
            message = temp[0] + ":\n" + temp[1].trim().split("\n")
                .mapIndexed { index, location ->
                    "${index + 1}. ${location.trim()}"
                }.joinToString("\n")
        }

        return MessageModel(
            user = user,
            message = message,
            tag = payload.tag
        )
    }

    fun modelToRequest(messageModel: MessageModel) =
        MessageRequest(messageModel.message, messageModel.tag)

    fun modelToEntity(messageModel: MessageModel): MessageEntity =
        messageModel.let {
            MessageEntity(
                chatId = it.id,
                message = it.message,
                user = it.user,
                tag = it.tag
            )
        }

    fun responseToEntity(payload: Payload): MessageEntity =
        modelToEntity(responseToModel(payload))

    fun mapEntitiesToDomain(entities: List<MessageEntity>): List<MessageModel> {
        return entities.map {
            MessageModel(
                id = it.chatId,
                user = it.user,
                message = it.message,
                tag = it.tag
            )
        }
    }
}