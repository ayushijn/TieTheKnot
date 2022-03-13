package com.my.tietheknot.api

import com.my.findurmatch.model.ApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

//private static final String BASE_URL = "https://randomuser.me/api/?results=10";
interface GetData {
    @GET("api/")
    fun getDataList(@Query("results") no: Int): Call<ApiResponse>
}