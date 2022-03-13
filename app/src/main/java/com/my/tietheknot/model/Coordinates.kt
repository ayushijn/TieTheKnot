package com.my.findurmatch.model

import com.google.gson.annotations.SerializedName

data class Coordinates(

	@field:SerializedName("latitude")
	val latitude: String,

	@field:SerializedName("longitude")
	val longitude: String
)