package com.deva.bangkit_capstoneproject.core.domain.model

import java.sql.Time
import java.util.*

data class MessageModel(
    val id: String = Calendar.getInstance().timeInMillis.toString(),
    val user: String,
    val message: String,
    val tag: String? = null,
    val payload: ArrayList<String>? = null
)