package com.traveloka.chatbot.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class UserCreationResponse(

	@field:SerializedName("message")
	val message: String
)
