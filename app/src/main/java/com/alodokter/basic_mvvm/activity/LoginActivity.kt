package com.alodokter.basic_mvvm.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.alfadigital.alfamikro.core.extension.saveLogin
import com.alodokter.basic_mvvm.R
import com.alodokter.basic_mvvm.model.response.LoginResponse
import com.alodokter.basic_mvvm.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private val viewModel by lazy {
        ViewModelProviders.of(this).get(LoginViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        init()
    }

    private fun init() {
        initViewModel()
        submitBtn.setOnClickListener { doSignIn() }
    }

    private fun initViewModel(){
        viewModel.resource.observe(this, Observer{
            when(it?.status) {
                Resource.LOADING -> onLoading()
                Resource.SUCCESS -> onSuccess(it.data as LoginResponse?)
                Resource.ERROR -> onFailure(it.error)
            }
        })
    }

    private fun doSignIn() {
        val email = etxEmail.text
        val password = etxPassword.text

        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            Toast.makeText(this,
                "Form tidak boleh kosong", Toast.LENGTH_SHORT).show()
        } else {
            viewModel.doLogin()
        }
    }

    private fun onFailure(t: Throwable) {
        progress_load.visibility = View.GONE
        Toast.makeText(this, "Failure $t", Toast.LENGTH_SHORT).show()
    }

    private fun onLoading() {
        progress_load.visibility = View.VISIBLE
    }

    private fun onSuccess(response: LoginResponse?) {
        saveLogin(response?.access_token!!, "Raka Purnama", "Male", "+628938399")
        progress_load.visibility = View.GONE
        Toast.makeText(this, "Login success", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, DashboardActivity::class.java))
        finish()
    }
}