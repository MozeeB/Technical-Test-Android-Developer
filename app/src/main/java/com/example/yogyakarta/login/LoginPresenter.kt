package com.example.yogyakarta.login

import android.widget.EditText
import com.example.yogyakarta.model.login.ResponseLogin
import com.example.yogyakarta.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Suppress("UNREACHABLE_CODE")
class LoginPresenter(model: LoginContruct.View) : LoginContruct.Presenter {

    var viemodel : LoginContruct.View? = null

    init {
        viemodel = model
    }

    override fun doLogin(email: String, password: String) {
        viemodel?.showLoading()
        val apiClient = ApiConfig.create()
        apiClient.doLogin(email, password).enqueue(object : Callback<ResponseLogin>{
            override fun onResponse(call: Call<ResponseLogin>, response: Response<ResponseLogin>) {
                viemodel?.hidelLoading()
                if (response.isSuccessful){
                    viemodel?.gettoken(response.body()?.data?.token)
                    viemodel?.getDataUser(response.body()?.data?.user?.name.toString(),
                        response.body()?.data?.user?.email.toString()
                    )
                    viemodel?.onSuccess(response.toString())
                }else{
                    viemodel?.onFailed(response.toString())
                }
            }

            override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                viemodel?.hidelLoading()
                viemodel?.onFailed(t.toString())
            }
        })
    }

    override fun loginClicked(email: EditText, password: EditText) {
        if (email.length() > 0 || password.length() > 0){
            viemodel?.showLogging()
        }else{
            viemodel?.showToastValidation()
            // percobaan yg keratusan kalinya ayo berhasil
        }
    }
}
