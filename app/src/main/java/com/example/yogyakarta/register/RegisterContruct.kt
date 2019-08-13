package com.example.yogyakarta.register;

import android.widget.EditText


interface RegisterContruct {

    interface View{
        fun onSuccess(message:String)
        fun onFailed(message: String)
        fun showToastValid()
        fun showProgress()
        fun showLogging()
        fun hideProgress()
    }
    interface Presenter{
        fun doRegister(name:String, email:String, password:String, c_password:String)
        fun checkValid(name:EditText, email: EditText, password: EditText, c_password: EditText)
    }

}
