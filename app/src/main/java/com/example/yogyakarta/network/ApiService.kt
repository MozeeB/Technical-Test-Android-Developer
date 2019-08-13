package com.example.yogyakarta.network

import com.example.yogyakarta.model.listData.ResponseList
import com.example.yogyakarta.model.login.ResponseLogin
import com.example.yogyakarta.model.register.ResponseRegister
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @FormUrlEncoded
    @POST("login")
    fun doLogin(@Field ("email") email:String,
                @Field ("password") password:String) : Call<ResponseLogin>


    @FormUrlEncoded
    @POST("register")
    fun doRegister(@Field ("name") name:String,
                   @Field("email") email: String,
                   @Field("password") password: String,
                   @Field("c_password") c_password:String) : Call<ResponseRegister>

    @GET("post")
    fun getData(@Header("Authorization") authToken: String,
                @Query("page") number:Int) : Call<ResponseList>
}