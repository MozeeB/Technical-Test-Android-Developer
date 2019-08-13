package com.example.yogyakarta.model.listData

import com.google.gson.annotations.SerializedName

data class ResponseList(

	@field:SerializedName("data")
	val data: List<DataItem?>? = null,

	@field:SerializedName("meta")
	val meta: Meta? = null,

	@field:SerializedName("status")
	val status: Status? = null
)