package com.example.yogyakarta.login

import android.widget.EditText

interface LoginContruct {

    interface View{
        fun onSuccess(messaeg:String)
        fun onFailed(messaeg: String)
        fun showLogging()
        fun showToastValidation()
        fun showLoading()
        fun hidelLoading()
        fun gettoken(token: String?)
        fun getDataUser(name: String, email: String)

    }

    interface Presenter{
        fun doLogin(email:String, password:String)
        fun loginClicked(email:EditText, password: EditText)
    }
}