package com.my.findurmatch.model

import com.google.gson.annotations.SerializedName

data class Info(

	@field:SerializedName("seed")
	val seed: String,

	@field:SerializedName("page")
	val page: String,

	@field:SerializedName("results")
	val results: String,

	@field:SerializedName("version")
	val version: String
)