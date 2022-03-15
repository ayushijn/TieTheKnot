package com.my.tietheknot.view

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.my.tietheknot.R
import com.my.tietheknot.databinding.SplashscreenBinding


class SplashActivity : AppCompatActivity() {
    private lateinit var binding: SplashscreenBinding
    private lateinit var animationDrawable: AnimationDrawable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.splashscreen)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
//        binding.splashiconimg.setBackgroundResource(R.drawable.blink_animation)
        binding.splashiconimg.setImageDrawable(resources.getDrawable(R.drawable.blink_animation))
        animationDrawable = binding.splashiconimg.drawable as AnimationDrawable
        Handler().postDelayed({
            animationDrawable.start()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 6000) // 3000 is the delayed time in milliseconds.
    }

    override fun onResume() {
        super.onResume()
        if (animationDrawable != null && !animationDrawable.isRunning()) {
            // start the animation
            animationDrawable.start()
        }
    }

    override fun onPause() {
        super.onPause()
        if (animationDrawable != null && animationDrawable.isRunning()) {
            // stop the animation
            animationDrawable.stop()
        }
    }

}