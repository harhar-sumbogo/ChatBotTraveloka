package com.deva.bangkit_capstoneproject.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class MessageResponse(

	@field:SerializedName("payload")
	val payload: Payload,

	@field:SerializedName("message")
	val message: String
)

data class Payload(

	@field:SerializedName("tag")
	val tag: String,

	@field:SerializedName("message")
	val message: String
)
