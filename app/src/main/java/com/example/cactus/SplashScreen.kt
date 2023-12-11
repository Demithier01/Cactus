package com.example.cactus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashScreen : AppCompatActivity() {
    private val spiashScreentimeout:Long=2000 //กำหนดเวลา 3 วินาที
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        //เรียก splash screen ทำงาน
        Handler().postDelayed({
            startActivity(Intent(this,Homepage::class.java))
            finish()
        },spiashScreentimeout)
    }
}