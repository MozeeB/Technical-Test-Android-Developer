package com.example.yogyakarta.model.register

import com.google.gson.annotations.SerializedName

data class ResponseRegister(

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("status")
	val status: Status? = null
)