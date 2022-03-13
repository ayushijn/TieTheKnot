package com.my.findurmatch.model

import com.google.gson.annotations.SerializedName

data class Location(

	@field:SerializedName("country")
	val country: String,

	@field:SerializedName("city")
	val city: String,

	@field:SerializedName("street")
	val street: Street,

	@field:SerializedName("timezone")
	val timezone: Timezone,

	@field:SerializedName("postcode")
	val postcode: String,

	@field:SerializedName("coordinates")
	val coordinates: Coordinates,

	@field:SerializedName("state")
	val state: String
)