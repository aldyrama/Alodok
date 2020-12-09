package com.alodokter.basic_mvvm.model.response

import com.alodokter.basic_mvvm.model.ProductModel
import java.io.Serializable

data class ProductResponse(
    var data: List<ProductModel>? = null
) : Serializable