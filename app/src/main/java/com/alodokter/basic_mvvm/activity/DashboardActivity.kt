package com.alodokter.basic_mvvm.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.alfadigital.alfamikro.core.extension.doLogout
import com.alodokter.basic_mvvm.MainActivity
import com.alodokter.basic_mvvm.R
import com.alodokter.basic_mvvm.fragment.AccountFragment
import com.alodokter.basic_mvvm.fragment.HomeFragment
import kotlinx.android.synthetic.main.layout_bottom_nav.*
import kotlinx.android.synthetic.main.toolbar.*

class DashboardActivity : AppCompatActivity() {

    private val homeFragment = HomeFragment()
    private val accountFragment = AccountFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        init()
    }

    private fun init(){
        toolbar.title = ""
        setSupportActionBar(toolbar)
        bottomNav()
        currentFragment(homeFragment)
    }

    private fun bottomNav() {
        navigation.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.home_menu -> currentFragment(homeFragment)
                R.id.account -> currentFragment(accountFragment)
            }
            true
        }
    }

    private fun currentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fly_wrap, fragment)
            commit()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflate = menuInflater
        inflate.inflate(R.menu.home_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logout -> {
                doLogout()
                startActivity(Intent(this, MainActivity:: class.java))
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}