package com.example.androidtest

import android.content.Intent
import com.airbnb.lottie.LottieAnimationView
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.example.androidtest.databinding.ActivitySplashBinding
import com.example.androidtest.R
import kotlin.time.Duration

class SplashActivity : AppCompatActivity() {

    private val DURATION: Long = 1500
    private lateinit var binding:ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySplashBinding.inflate(layoutInflater)
         setContentView(binding.root)
        binding.animationView.speed=2.0F
        binding.animationView.playAnimation()
        Handler().postDelayed({
            val intent = Intent(this,MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
            finish()
        }, DURATION)
    }

}