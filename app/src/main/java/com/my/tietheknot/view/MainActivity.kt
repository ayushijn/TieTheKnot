package com.my.tietheknot.view

import android.app.ProgressDialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
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
import com.my.tietheknot.viewmodel.MainViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var progressDialog: ProgressDialog
    private var dataList: ArrayList<ResultsItem> = ArrayList()
    private lateinit var adapter: MyRecyclerviewAdapter
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        setAdapter()
        viewModel.getMatchListData()
        viewModel.getResultList(this)
        viewModel.listData.observe(this, androidx.lifecycle.Observer {
            parseResponse(it)
        })


    }

    private fun parseResponse(list: ArrayList<ResultsItem>?) {
        showProgressBar()
        list?.let {
            dismiss()
            dataList.clear();
            dataList.addAll(it)
            adapter.notifyDataSetChanged()
        } ?: run {
            Toast.makeText(this@MainActivity,"Something went wrong",Toast.LENGTH_SHORT).show()
            dismiss()
        }
    }

    private fun dismiss() {
        progressDialog.dismiss()
    }

    private fun showProgressBar() {
        progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Loading...")
        progressDialog.show()
    }

    private fun setAdapter() {
        adapter = MyRecyclerviewAdapter(dataList, object : RecyclerViewClickListener {
            override fun onClick(view: View?, pos: Int) {
//                Toast.makeText(this@MainActivity , "Error",Toast.LENGTH_SHORT).show()

            }
        })
        PagerSnapHelper().attachToRecyclerView(binding.rcView)
        binding.adapter = adapter
        binding.rcView.addItemDecoration(LinePagerIndicatorDecoration())

    }
}