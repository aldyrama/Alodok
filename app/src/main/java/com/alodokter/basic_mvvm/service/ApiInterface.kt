package com.alodokter.basic_mvvm.service

import com.alodokter.basic_mvvm.model.response.LoginResponse
import com.alodokter.basic_mvvm.model.response.ProductResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {

    @POST("/login")
    fun doLogin(): Call<LoginResponse>

    @GET("/products")
    fun getHome(): Call<ProductResponse>

}