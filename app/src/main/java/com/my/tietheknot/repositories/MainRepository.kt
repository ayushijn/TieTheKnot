package com.my.tietheknot.repositories

import android.app.Activity
import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.my.findurmatch.model.ApiResponse
import com.my.findurmatch.model.ResultsItem
import com.my.tietheknot.api.ApiClient
import com.my.tietheknot.api.GetData
import com.my.tietheknot.db.MyDatabase
import com.my.tietheknot.db.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainRepository {
    fun requestData(mutableLiveData: MutableLiveData<ArrayList<ResultsItem>>): MutableLiveData<ArrayList<ResultsItem>> {
        val call: Call<ApiResponse>? =
            ApiClient.retrofitInstance?.create(GetData::class.java)?.getDataList(10)
        call?.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                mutableLiveData.postValue(response.body()?.results)
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                mutableLiveData.postValue(null)
                Log.e("fail", "Response from retrofit failed")
            }
        })

        return mutableLiveData
    }




    class GetDataOperation(var activity: Activity, var userList: MutableLiveData<List<User>>) :
        AsyncTask<String?, Void?, List<User>?>() {
        override fun onPreExecute() {}
        override fun onPostExecute(result: List<User>?) {
            if (result != null) {
                userList.postValue(result)
            }
        }
        override fun doInBackground(vararg p0: String?): List<User>? {
            return MyDatabase.getInstance(activity)?.dao?.getUserList()
        }
    }

    fun getUserList(activity: Activity,  userList: MutableLiveData<List<User>>){
        GetDataOperation(activity, userList).execute()

    }

    class InsertDataOperation(var activity: Activity, var user: User) :
        AsyncTask<Void, Void, Any>() {
        override fun onPreExecute() {}

        override fun doInBackground(vararg params: Void?): Any {
            MyDatabase.getInstance(activity)?.dao?.insert(user)
            return ""
        }
    }

    fun addUser(activity: Activity,  user: User){
        InsertDataOperation(activity, user).execute()

    }
}