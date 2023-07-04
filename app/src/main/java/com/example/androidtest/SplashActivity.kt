package com.example.androidtest

import android.content.Intent
import com.airbnb.lottie.LottieAnimationView
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.androidtest.MainActivity
import com.example.androidtest.R
import kotlin.time.Duration

class SplashActivity : AppCompatActivity() {

    private val DURATION: Long = 1500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         setContentView(R.layout.activity_splash)

        var loadingImage = findViewById(R.id.animationView) as LottieAnimationView

        // 애니메이션 시작
        loadingImage.playAnimation()


        Handler().postDelayed({
            val intent = Intent(this,MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
            finish()
        }, DURATION)
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}