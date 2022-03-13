package com.my.findurmatch.model

import com.google.gson.annotations.SerializedName

data class Street(

	@field:SerializedName("number")
	val number: String,

	@field:SerializedName("name")
	val name: String
)