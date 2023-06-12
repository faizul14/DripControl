package com.faezolfp.dripcontrol.presentation.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.faezolfp.dripcontrol.R
import com.faezolfp.dripcontrol.databinding.ActivityLoginBinding
import com.faezolfp.dripcontrol.presentation.register.RegisterActivity

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        displayBUtton()
    }

    private fun displayBUtton() {
        binding.txtRegister.setOnClickListener(this)
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.txt_register ->{
                startActivity(Intent(this, RegisterActivity::class.java))
            }
        }
    }
}