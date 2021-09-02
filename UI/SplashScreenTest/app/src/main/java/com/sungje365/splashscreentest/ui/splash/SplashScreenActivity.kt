package com.sungje365.splashscreentest.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.sungje365.splashscreentest.R
import com.sungje365.splashscreentest.databinding.ActivitySplashScreenBinding
import com.sungje365.splashscreentest.ui.main.MainActivity

class SplashScreenActivity : AppCompatActivity() {
    companion object {
        private const val SCREEN_DURATION = 2000L
    }

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash_screen)
        binding.ivSplashBackground.apply {
            alpha = 0f
            animate().setDuration(SCREEN_DURATION).alpha(1f).withEndAction {
                startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                finish()
            }
        }
    }
}