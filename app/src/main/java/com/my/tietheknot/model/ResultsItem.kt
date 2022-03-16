package com.my.findurmatch.model

import com.google.gson.annotations.SerializedName

data class ResultsItem(

    @field:SerializedName("nat")
    var nat: String,

    @field:SerializedName("gender")
    var gender: String,

    @field:SerializedName("phone")
    var phone: String,

    @field:SerializedName("dob")
    var dob: Dob,

    @field:SerializedName("name")
    var name: Name,

    @field:SerializedName("registered")
    var registered: Registered,

    @field:SerializedName("location")
    var location: Location,

    @field:SerializedName("id")
    var id: Id,

    @field:SerializedName("login")
    var login: Login,

    @field:SerializedName("cell")
    var cell: String,

    @field:SerializedName("email")
    var email: String,

    @field:SerializedName("picture")
    var picture: Picture,

    @field:SerializedName("status")
    var status: Int
)