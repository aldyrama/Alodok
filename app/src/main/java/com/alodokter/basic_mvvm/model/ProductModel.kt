package com.alodokter.basic_mvvm.model

import java.io.Serializable

data class ProductModel(
    val id: Int?,
    val name: String?,
    val image: String?,
    var imgList: List<String>? = null
) : Serializable