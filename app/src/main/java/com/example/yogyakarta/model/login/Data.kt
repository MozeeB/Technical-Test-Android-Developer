package com.example.yogyakarta.model.login

import com.google.gson.annotations.SerializedName

data class Data(

	@field:SerializedName("user")
	val user: User? = null,

	@field:SerializedName("token")
	val token: String? = null


)