package com.alodokter.basic_mvvm.base

interface BaseFragmentView<PV> {
    fun refresh()
    fun reload()
    fun setParentView(view : PV)
    fun reattach()
}