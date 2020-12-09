package com.alodokter.basic_mvvm.viewmodel

import Resource
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alodokter.basic_mvvm.model.ProductModel
import com.alodokter.basic_mvvm.model.response.ProductResponse
import com.alodokter.basic_mvvm.service.ApiUtils
import com.alodokter.basic_mvvm.utils.Constant
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.nio.charset.Charset

class HomeViewModel: ViewModel(), LifecycleObserver {

    val resource = MutableLiveData<Resource<Any>>()
    val dataList = MutableLiveData<ArrayList<ProductModel>>()

    init {
        dataList.value = ArrayList()
    }

    fun onRefreshHome(){
        dataList.value?.clear()
        getHome()
    }

    fun getHome(){
        resource.postValue(Resource.loading())
        val call = ApiUtils.getInterface(Constant.baseUrl).getHome()

        call.enqueue(object : Callback<ProductResponse> {
            override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                resource.postValue(Resource.error(t))
            }

            override fun onResponse(call: Call<ProductResponse>, response: Response<ProductResponse>) {
                if (response.isSuccessful){
                    val list = ArrayList<ProductModel>()
                    dataList.value?.clear()
                    dataList.value?.let { list.addAll(it)}
                    val newList = response.body()?.data
                    if (!newList.isNullOrEmpty()) list.addAll(newList)

                    dataList.postValue(list)
                    resource.postValue(Resource.success(response.body()))
                }else {
                    val errorBytes = response.errorBody()!!.bytes()
                    val errorBody = String(errorBytes, Charset.forName("UTF-8"))
                    resource.postValue(Resource.error(Exception(errorBody)))
                }
            }

        })
    }
}