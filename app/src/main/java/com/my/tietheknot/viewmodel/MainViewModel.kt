package com.my.tietheknot.viewmodel

import android.app.Activity
import android.os.AsyncTask
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.my.findurmatch.model.ResultsItem
import com.my.tietheknot.db.MyDatabase
import com.my.tietheknot.db.User
import com.my.tietheknot.repositories.MainRepository


class MainViewModel() : ViewModel() {
     var listData = MutableLiveData<ArrayList<ResultsItem>>()
    var userList = MutableLiveData<List<User>>()
    private var mainRepository: MainRepository = MainRepository()

    fun getMatchListData() {
        listData.postValue(null)
        mainRepository.requestData(listData)
    }




    fun getResultList(activity: Activity) {
        mainRepository.getUserList(activity, userList)
    }
    fun addUser(activity: Activity,user : User) {
        mainRepository.addUser(activity, user)
    }

}