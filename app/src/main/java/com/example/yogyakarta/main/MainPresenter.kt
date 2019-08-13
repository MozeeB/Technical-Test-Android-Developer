package com.example.yogyakarta.main

import com.example.yogyakarta.model.listData.DataItem
import com.example.yogyakarta.model.listData.ResponseList
import com.example.yogyakarta.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Suppress("UNCHECKED_CAST")
class MainPresenter(model: MainContruct.View): MainContruct.Presenter {

    var view :MainContruct.View? = null

    init {
        view = model
    }
    override fun getData(getToken: String, number: Int) {
        view?.showProgress()
        ApiConfig.create().getData(getToken,number).enqueue(object : Callback<ResponseList>{
            override fun onResponse(call: Call<ResponseList>, response: Response<ResponseList>) {
                view?.hideProgress()
                if (response.isSuccessful){
                    view?.showdata(response.body()?.data as List<DataItem>)
                }else{
                    view?.onFailed(response.toString())
                }
            }

            override fun onFailure(call: Call<ResponseList>, t: Throwable) {
                view?.onFailed(t.toString())
            }
        })
    }
}