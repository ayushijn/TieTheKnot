package com.my.findurmatch.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.INVISIBLE
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.my.findurmatch.interfaces.RecyclerViewClickListener
import com.my.findurmatch.model.ResultsItem
import com.my.tietheknot.R
import com.my.tietheknot.databinding.RcItemBinding
import java.util.*


class MyRecyclerviewAdapter(
    private val mList: ArrayList<ResultsItem>,
    private val listener: RecyclerViewClickListener
) :
    RecyclerView.Adapter<MyRecyclerviewAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = DataBindingUtil.inflate<RcItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.rc_item,
            parent,
            false
        )
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.binding.model = mList[position]
        holder.binding.acceptBtn.setOnClickListener {
            listener.onClick(holder.binding.acceptBtn, position)
        }
        holder.binding.declineBtn.setOnClickListener {
            listener.onClick(holder.binding.declineBtn, position)
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    inner class MyViewHolder(val binding: RcItemBinding) :
        ViewHolder(binding.root)
}