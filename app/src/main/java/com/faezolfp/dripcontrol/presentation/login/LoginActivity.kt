package com.faezolfp.dripcontrol.presentation.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.faezolfp.dripcontrol.MainActivity2
import com.faezolfp.dripcontrol.R
import com.faezolfp.dripcontrol.SplashViewModel
import com.faezolfp.dripcontrol.core.utils.ViewModelFactory
import com.faezolfp.dripcontrol.databinding.ActivityLoginBinding
import com.faezolfp.dripcontrol.presentation.register.RegisterActivity

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        val factory = ViewModelFactory.getInstance(application)
        viewModel = ViewModelProvider(this, factory).get(LoginViewModel::class.java)

        displayBUtton()
    }

    private fun displayBUtton() {
        binding.txtRegister.setOnClickListener(this)
        binding.btnRegister.setOnClickListener(this)
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.txt_register ->{
                startActivity(Intent(this, RegisterActivity::class.java))
            }
            R.id.btn_register ->{
                viewModel.login()
                startActivity(Intent(this, MainActivity2::class.java))
                finish()
            }
        }
    }
}