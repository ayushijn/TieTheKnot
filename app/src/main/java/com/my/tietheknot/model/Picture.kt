package com.my.findurmatch.model

import com.google.gson.annotations.SerializedName

data class Picture(

	@field:SerializedName("thumbnail")
	val thumbnail: String,

	@field:SerializedName("large")
	val large: String,

	@field:SerializedName("medium")
	val medium: String
)