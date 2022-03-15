package com.my.tietheknot.viewmodel

import android.app.Activity
import android.os.AsyncTask
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.my.findurmatch.model.ResultsItem
import com.my.tietheknot.db.Database
import com.my.tietheknot.db.User
import com.my.tietheknot.repositories.MainRepository


class MainViewModel() : ViewModel() {
    public var listData = MutableLiveData<ArrayList<ResultsItem>>()
    var userList = MutableLiveData<List<User>>()
    private var mainRepository: MainRepository = MainRepository()

    fun getMatchListData() {
        listData.postValue(null)
        mainRepository.requestData(listData)
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
            return Database.getInstance(activity)?.dao?.getUserList()
        }
    }

    fun getResultList(activity: Activity) {
        GetDataOperation(activity, userList).execute()
    }

}