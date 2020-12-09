package com.alodokter.basic_mvvm.base

interface BaseActivity {
    fun refresh()
    fun reload()
    fun onFailure(t: Throwable)
    fun onLoading()
    fun onSuccess()
}