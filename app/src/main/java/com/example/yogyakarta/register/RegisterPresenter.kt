package com.example.yogyakarta.register;

import android.util.Patterns
import android.widget.EditText
import com.example.yogyakarta.model.register.ResponseRegister
import com.example.yogyakarta.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterPresenter(model: RegisterContruct.View) : RegisterContruct.Presenter {

    var view: RegisterContruct.View? = null

    init {
        view = model
    }

    override fun doRegister(name: String, email: String, password: String, c_password: String) {
        view?.showProgress()
        ApiConfig.create().doRegister(name, email, password, c_password).enqueue(object : Callback<ResponseRegister>{
            override fun onResponse(call: Call<ResponseRegister>, response: Response<ResponseRegister>) {
                view?.hideProgress()
                if (response.isSuccessful){
                    view?.onSuccess(response.toString())
                }else{
                    view?.onFailed(response.toString())
                }
            }

            override fun onFailure(call: Call<ResponseRegister>, t: Throwable) {
                view?.hideProgress()
                view?.onFailed(t.toString())
            }
        })

    }

    override fun checkValid(name: EditText, email: EditText, password: EditText, c_password: EditText) {
        if (name.length() > 0 || email.length() > 0 || password.length() > 0 || c_password.length() > 0 ||
            Patterns.EMAIL_ADDRESS.matcher(email.toString()).matches()){
            view?.showLogging()

        }else{
            view?.showToastValid()

        }
    }

}
