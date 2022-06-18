package com.canerture.booksapp.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.canerture.booksapp.R
import com.canerture.booksapp.common.Constants.SIGN_IN
import com.canerture.booksapp.common.Constants.SIGN_UP
import com.canerture.booksapp.databinding.ActivityLoginBinding
import com.canerture.booksapp.ui.main.MainActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        Firebase.auth.currentUser?.let {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        val titleList = arrayListOf(SIGN_IN, SIGN_UP)

        binding.viewPager.adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)

        TabLayoutMediator(binding.loginTabLayout, binding.viewPager) { tab, position ->
            tab.text = titleList[position]
        }.attach()
    }
}