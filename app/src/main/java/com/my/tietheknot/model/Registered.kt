package com.my.findurmatch.model

import com.google.gson.annotations.SerializedName

data class Registered(

	@field:SerializedName("date")
	val date: String,

	@field:SerializedName("age")
	val age: String
)