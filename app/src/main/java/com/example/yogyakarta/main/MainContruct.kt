package com.example.yogyakarta.main

import com.example.yogyakarta.model.listData.DataItem

interface MainContruct {
    interface View{
        fun onFailed(message: String)
        fun showProgress()
        fun hideProgress()
        fun showdata(dataItem: List<DataItem>)
    }
    interface Presenter{
        fun getData(getToken: String, number:Int)
    }
}