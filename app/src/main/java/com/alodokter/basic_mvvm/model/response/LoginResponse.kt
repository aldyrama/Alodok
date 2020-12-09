package com.alodokter.basic_mvvm.model.response

import com.alodokter.basic_mvvm.model.Meta
import java.io.Serializable

data class LoginResponse(
    val meta: Meta?,
    val access_token: String?,
    var expiry_date: Boolean?
) : Serializable