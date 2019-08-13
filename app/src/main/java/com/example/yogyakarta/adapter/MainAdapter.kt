package com.example.yogyakarta.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.yogyakarta.DetailActivity
import com.example.yogyakarta.R
import com.example.yogyakarta.model.listData.DataItem

class MainAdapter(c: Context?, data: List<DataItem>?) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {


    var dataItem: List<DataItem>? = data
    var mContext: Context? = c

    override fun getItemCount(): Int {
        return dataItem?.size!!
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val inflater = LayoutInflater.from(mContext).inflate(R.layout.item_list, p0, false)
        return ViewHolder(inflater)
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {


        p0.judul?.text = dataItem?.get(p1)?.title
        p0.category?.text = dataItem?.get(p1)?.category
        p0.tanggal?.text = dataItem?.get(p1)?.date
        Glide.with(mContext!!).load(dataItem?.get(p1)?.image).into(p0.imagPem!!)


        p0.itemView.setOnClickListener {
            val intent = Intent(mContext, DetailActivity::class.java)
            intent.putExtra("judul", dataItem?.get(p1)?.title)
            intent.putExtra("categori",dataItem?.get(p1)?.category)
            intent.putExtra("tanggal", dataItem?.get(p1)?.date)
            intent.putExtra("deskripsi", dataItem?.get(p1)?.description)
            intent.putExtra("img", dataItem?.get(p1)?.image)
            mContext?.startActivity(intent)
        }
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        var judul = itemView?.findViewById<TextView>(R.id.tvJudulList)
        var category = itemView?.findViewById<TextView>(R.id.tvkat)
        var tanggal = itemView?.findViewById<TextView>(R.id.tvdate)
        var imagPem = itemView?.findViewById<ImageView>(R.id.imgList)



    }

}

