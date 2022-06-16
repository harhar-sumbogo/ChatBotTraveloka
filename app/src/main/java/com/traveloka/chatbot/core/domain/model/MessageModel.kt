package com.traveloka.chatbot.core.domain.model

import java.util.*

data class MessageModel(
    val id: String = Calendar.getInstance().timeInMillis.toString(),
    val user: String,
    val message: String,
    val tag: String? = null,
)