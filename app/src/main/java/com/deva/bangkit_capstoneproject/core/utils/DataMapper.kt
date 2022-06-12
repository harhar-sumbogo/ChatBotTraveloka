package com.deva.bangkit_capstoneproject.core.utils

import com.deva.bangkit_capstoneproject.core.data.remote.request.MessageRequest
import com.deva.bangkit_capstoneproject.core.data.remote.response.Payload
import com.deva.bangkit_capstoneproject.core.domain.model.MessageModel

object DataMapper {
    fun responseToModel(payload: Payload, user: String = "BOT"): MessageModel {
        return MessageModel(
            user = user,
            message= payload.message,
            tag = payload.tag
        )
    }

    fun modelToRequest(messageModel: MessageModel) =
        MessageRequest(messageModel.message, messageModel.tag)
}