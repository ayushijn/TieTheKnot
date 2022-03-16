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
import com.my.findurmatch.model.ResultsItem
import com.my.tietheknot.R
import com.my.tietheknot.databinding.ActivityMainBinding
import com.my.tietheknot.db.User
import com.my.tietheknot.viewmodel.MainViewModel
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
        viewModel.listData.observe(this, androidx.lifecycle.Observer {
            parseResponse(it)
        })
        viewModel.userList.observe(this, androidx.lifecycle.Observer {
            parseDBResponse(it)
        })


    }

    private fun parseDBResponse(users: List<User>?) {

        users?.let {
            if (users.size > 0 && dataList.size > 0) {
                for (i in users.indices) {
                    for (j in dataList.indices) {
                        if (users[i].email.equals(dataList[j].email)) {
                            dataList[j].status = users[i].status
                        }
                    }

                }
            }
        }
        adapter.notifyDataSetChanged()
    }

    private fun parseResponse(list: ArrayList<ResultsItem>?) {
        showProgressBar()
        list?.let {
            dismiss()
            dataList.clear()
            dataList.addAll(it)
            viewModel.getResultList(this)
        } ?: run {
            //Toast.makeText(this@MainActivity, "Something went wrong", Toast.LENGTH_SHORT).show()
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
                when (view?.id) {
                    R.id.accept_btn -> {
                        dataList[pos].status = 1
                        viewModel.addUser(
                            this@MainActivity,
                            User(dataList[pos].email, dataList[pos].status)
                        )
                    }

                    R.id.decline_btn -> {
                        dataList[pos].status = 2
                        viewModel.addUser(
                            this@MainActivity,
                            User(dataList[pos].email, dataList[pos].status)
                        )
                    }

                }
                adapter.notifyItemChanged(pos)
            }
        })
        PagerSnapHelper().attachToRecyclerView(binding.rcView)
        binding.adapter = adapter
        binding.rcView.addItemDecoration(LinePagerIndicatorDecoration())

    }
}