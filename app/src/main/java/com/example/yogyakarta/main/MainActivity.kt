package com.example.yogyakarta.main

import android.app.ProgressDialog
import android.app.ProgressDialog.show
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.yogyakarta.R
import com.example.yogyakarta.UserActivity
import com.example.yogyakarta.adapter.MainAdapter
import com.example.yogyakarta.model.listData.DataItem
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.orhanobut.hawk.Hawk
import dmax.dialog.SpotsDialog
import es.dmoral.toasty.Toasty
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContruct.View, View.OnClickListener {


    lateinit var presenter: MainPresenter
    lateinit var rv_main : RecyclerView
    lateinit var tok :String
    var increment:Int = 1
    var dialog:android.app.AlertDialog? = null
    //halo nama saya xixixixi dan dadadada


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MainPresenter(this)
        rv_main = this.findViewById(R.id.rvYogya)
        rvYogya.setHasFixedSize(true)


        dialog = SpotsDialog.Builder()
            .setContext(this)
            .setMessage("Loading...")
            .setCancelable(false)
            .build()


        tok  = Hawk.get("token", "")
        presenter.getData("Bearer " + tok,increment)

        imgUserPorf.setOnClickListener(this)
        imgBackArrow.setOnClickListener(this)
        imgNextArrow.setOnClickListener(this)

        MobileAds.initialize(this)

        val adRequest = AdRequest.Builder()
            .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
            .build()
        adView.loadAd(adRequest)

        adView.adListener = object: AdListener() {
            override fun onAdLoaded() {
                Log.i("LOG","Banner Loaded")
                Toast.makeText(applicationContext, "Iklan Selesai Dimuat", Toast.LENGTH_SHORT).show()
                super.onAdLoaded()

            }
            override fun onAdFailedToLoad(errorCode : Int) {
                Log.i("LOG","Banner Failed to load")
                Toast.makeText(applicationContext, "Iklan Gagal Dimuat", Toast.LENGTH_SHORT).show()
                super.onAdFailedToLoad(errorCode)

            }
        }


    }



    override fun onClick(v: View?) {
        when(v?.id){
            R.id.imgUserPorf -> {
                startActivity(Intent(this, UserActivity::class.java))

            }
            R.id.imgBackArrow -> {
                val incre: Int = increment--
                presenter.getData("Bearer " + tok, incre )
            }
            R.id.imgNextArrow ->{
                val tam: Int = increment++
                presenter.getData("Bearer " + tok, tam)
            }
        }

    }



    override fun onFailed(message: String) {
        Toasty.error(this, "Load failed!", Toasty.LENGTH_SHORT).show()
    }

    override fun showProgress() {
        dialog?.show()
        rv_main.visibility = View.GONE
    }

    override fun hideProgress() {
        Handler().postDelayed({
            dialog?.dismiss()
            rv_main.visibility = View.VISIBLE
        }, 1000)
    }

    override fun showdata(dataItem: List<DataItem>) {
        rv_main.layoutManager = LinearLayoutManager(this)
        rv_main.adapter = MainAdapter(this, dataItem)
    }


}
