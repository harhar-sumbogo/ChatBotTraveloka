package com.deva.bangkit_capstoneproject.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class UserCreationResponse(

	@field:SerializedName("message")
	val message: String
)
