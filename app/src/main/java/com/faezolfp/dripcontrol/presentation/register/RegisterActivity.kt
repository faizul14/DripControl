package com.faezolfp.dripcontrol.presentation.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.faezolfp.dripcontrol.R
import com.faezolfp.dripcontrol.databinding.ActivityRegisterBinding
import com.faezolfp.dripcontrol.presentation.login.LoginActivity

class RegisterActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        displayButton()
    }

    private fun displayButton() {
        binding.txtLogin.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.txt_login -> {
                finish()
            }
        }
    }
}