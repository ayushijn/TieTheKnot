package com.my.tietheknot.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.my.findurmatch.model.ApiResponse
import com.my.findurmatch.model.ResultsItem
import com.my.tietheknot.api.ApiClient
import com.my.tietheknot.api.GetData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainRepository {
    fun requestData( mutableLiveData: MutableLiveData<ArrayList<ResultsItem>>): MutableLiveData<ArrayList<ResultsItem>> {
        val call: Call<ApiResponse>? =
            ApiClient.retrofitInstance?.create(GetData::class.java)?.getDataList(10)
          call?.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                mutableLiveData.postValue(response.body()?.results)
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                mutableLiveData.postValue(null)
                Log.e("fail","Response from retrofit failed")
            }
        })

        return mutableLiveData
    }

    }
