package com.canerture.booksapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.canerture.booksapp.databinding.ActivitySplashBinding
import com.canerture.booksapp.ui.login.LoginActivity
import com.canerture.booksapp.ui.main.MainActivity

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val background = object : Thread() {
            override fun run() {
                try {

                    binding.animatedSvgView.start()

                    sleep(3000)

                    val intent = Intent(this@SplashActivity, LoginActivity::class.java)
                    startActivity(intent)

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        background.start()
    }
}