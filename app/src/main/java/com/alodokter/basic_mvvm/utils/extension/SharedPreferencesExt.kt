package com.alfadigital.alfamikro.core.extension

import android.annotation.SuppressLint
import android.content.Context


const val PREFS_NAME = "alodoktertest_prefs"
/** -- **/
const val ACCESS_TOKEN = "access_token"
const val IS_LOGIN = "is_login"


// --

fun Context.isLogin(): Boolean {
    return this.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        .getBoolean(IS_LOGIN, false)
}

@SuppressLint("ApplySharedPref")
fun Context.saveLogin(token: String, name: String, gender: String, phone: String) {
    this.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        .edit()
        .putString(ACCESS_TOKEN, token)
        .putBoolean(IS_LOGIN, true)
        .commit()
}


fun Context.doLogout() {
  this.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
      .edit()
      .remove(ACCESS_TOKEN)
      .remove(IS_LOGIN)
      .apply()
}