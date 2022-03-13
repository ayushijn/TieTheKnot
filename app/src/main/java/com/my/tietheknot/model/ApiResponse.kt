package com.my.findurmatch.model

import com.google.gson.annotations.SerializedName


data class ApiResponse(

	@field:SerializedName("results")
	val results: ArrayList<ResultsItem>,

	@field:SerializedName("info")
	val info: Info
)