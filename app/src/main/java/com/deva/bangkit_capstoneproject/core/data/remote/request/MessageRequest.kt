package com.deva.bangkit_capstoneproject.core.data.remote.request

import com.google.gson.annotations.SerializedName

data class MessageRequest(

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("tag")
	val tag: String? = null

)
