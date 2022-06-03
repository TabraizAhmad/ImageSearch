package com.electrolux.imagesearchapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.FragmentActivity
import com.electrolux.imagesearchapp.MainActivity
import com.electrolux.imagesearchapp.R
import com.electrolux.imagesearchapp.utils.Constants.SPLASH_DELAY
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // applying delay and move to main activity

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, SPLASH_DELAY)
    }
}