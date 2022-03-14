package com.my.tietheknot.view

import android.app.ProgressDialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.recylerviewasviewpager.LinePagerIndicatorDecoration
import com.my.findurmatch.adapters.MyRecyclerviewAdapter
import com.my.findurmatch.interfaces.RecyclerViewClickListener
import com.my.findurmatch.model.ApiResponse
import com.my.findurmatch.model.ResultsItem
import com.my.tietheknot.R
import com.my.tietheknot.api.ApiClient
import com.my.tietheknot.api.GetData
import com.my.tietheknot.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var progressDialog: ProgressDialog
    private var dataList: ArrayList<ResultsItem> = ArrayList()
    private lateinit var adapter: MyRecyclerviewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setAdapter()
        getServerData()
    }


    private fun getServerData() {
        progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Loading...")
        progressDialog.show()

        val call: Call<ApiResponse>? =
            ApiClient.retrofitInstance?.create(GetData::class.java)?.getDataList(10)
        call?.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                progressDialog.dismiss()
                if (response.body() != null) {
                    dataList.clear()
                    dataList.addAll(response.body()?.results!!)
                    adapter.notifyDataSetChanged()
                }

            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                progressDialog.dismiss()
                Toast.makeText(this@MainActivity , "Error",Toast.LENGTH_SHORT).show()
            }
        })


    }


    private fun setAdapter() {
        adapter = MyRecyclerviewAdapter(dataList, object :RecyclerViewClickListener{
            override fun onClick(view: View?, pos: Int) {
//                Toast.makeText(this@MainActivity , "Error",Toast.LENGTH_SHORT).show()

            }
        })
        PagerSnapHelper().attachToRecyclerView(binding.rcView)
        binding.adapter = adapter
        binding.rcView.addItemDecoration(LinePagerIndicatorDecoration())

    }
}