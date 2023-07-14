package com.faezolfp.dripcontrol

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.faezolfp.dripcontrol.core.utils.ViewModelFactory
import com.faezolfp.dripcontrol.databinding.ActivitySplashScreenBinding
import com.faezolfp.dripcontrol.presentation.login.LoginActivity

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val factory = ViewModelFactory.getInstance(application)
        val viewModel = ViewModelProvider(this, factory).get(SplashViewModel::class.java)

        Handler().postDelayed({
            viewModel.getIsLogin.observe(this) { data ->
                if (data) {
                    startActivity(Intent(this, MainActivity2::class.java))
                } else {
                    startActivity(Intent(this, LoginActivity::class.java))
                }
            }
            finish()
        }, 5000)
    }

    fun destination() {
        lifecycleScope.launchWhenStarted {
        }
    }
}
