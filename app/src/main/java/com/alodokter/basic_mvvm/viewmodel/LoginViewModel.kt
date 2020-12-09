package com.alodokter.basic_mvvm.viewmodel

import Resource
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alodokter.basic_mvvm.model.response.LoginResponse
import com.alodokter.basic_mvvm.service.ApiUtils
import com.alodokter.basic_mvvm.utils.Constant
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.nio.charset.Charset

class LoginViewModel : ViewModel(), LifecycleObserver {

    val resource = MutableLiveData<Resource<Any>>()

    fun doLogin() {
        resource.postValue(Resource.loading())
        val call = ApiUtils.getInterface(Constant.baseUrl).doLogin()

        call.enqueue(object : Callback<LoginResponse> {
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                resource.postValue(Resource.error(t))
            }

            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    resource.postValue(Resource.success(response.body()))
                } else {
                    val errorBytes = response.errorBody()!!.bytes()
                    val errorBody = String(errorBytes, Charset.forName("UTF-8"))
                    resource.postValue(Resource.error(Exception(errorBody)))
                }
            }

        })
    }
}