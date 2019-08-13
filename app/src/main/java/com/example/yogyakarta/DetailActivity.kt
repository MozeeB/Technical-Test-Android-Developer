package com.example.yogyakarta

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)
        collapsing_toolbar.setTitle("Details " + intent.getStringExtra("judul"))
        collapsing_toolbar.setCollapsedTitleTextColor(
            ContextCompat.getColor(this, R.color.white))
        collapsing_toolbar.setExpandedTitleColor(
            ContextCompat.getColor(this, R.color.colorPrimary))


        tvJudulDetail.setText(intent.getStringExtra("judul"))
        tvKategoriDetail.setText(intent.getStringExtra("categori"))
        tvTgl.setText(intent.getStringExtra("tanggal"))
        tvDes.setText(intent.getStringExtra("deskripsi"))
        Glide.with(this).load(intent.getStringExtra("img")).into(imgDetail)
    }

}
