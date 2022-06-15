package com.deva.bangkit_capstoneproject.core.utils

import android.util.Log
import com.deva.bangkit_capstoneproject.core.data.remote.request.MessageRequest
import com.deva.bangkit_capstoneproject.core.data.remote.response.Payload
import com.deva.bangkit_capstoneproject.core.domain.model.MessageModel

object DataMapper {
    fun responseToModel(payload: Payload, user: String = "BOT"): MessageModel {
        val recommendation = ArrayList<String>()

        val temp = payload.message.split(":")
        val message = temp[0]

        if (temp.size > 1) {
            for (item in temp[1].trim().split("\n")) {
                recommendation.add(item.trim())
            }
        }

        return MessageModel(
            user = user,
            message= message,
            tag = payload.tag,
            payload = if (recommendation.size > 0) recommendation else null
        )
    }

    fun modelToRequest(messageModel: MessageModel) =
        MessageRequest(messageModel.message, messageModel.tag)
}