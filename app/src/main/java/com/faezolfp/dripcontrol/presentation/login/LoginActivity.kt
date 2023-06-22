package com.faezolfp.dripcontrol.presentation.login

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.faezolfp.dripcontrol.MainActivity2
import com.faezolfp.dripcontrol.R
import com.faezolfp.dripcontrol.core.utils.ViewModelFactory
import com.faezolfp.dripcontrol.databinding.ActivityLoginBinding
import com.faezolfp.dripcontrol.presentation.register.RegisterActivity
import com.jakewharton.rxbinding2.widget.RxTextView

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

        val emailStream = RxTextView.textChanges(binding.edtEmail).skipInitialValue().map { email ->
            !Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }
        emailStream.subscribe {
            edtEmailAllert(it)
        }
        val passtream = RxTextView.textChanges(binding.edtPass).skipInitialValue().map { password ->
            password.length < 6
        }
        passtream.subscribe {
            edtPassAllert(it)
        }
    }

    private fun displayBUtton() {
        binding.txtRegister.setOnClickListener(this)
        binding.btnRegister.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.txt_register -> {
                startActivity(Intent(this, RegisterActivity::class.java))
            }
            R.id.btn_register -> {
                viewModel.login()
                startActivity(Intent(this, MainActivity2::class.java))
                finish()
            }
        }
    }

    private fun edtEmailAllert(isNotValid: Boolean) {
        binding.edtEmail.error = if (isNotValid) "Email tidak valid" else null
    }

    private fun edtPassAllert(isNotValid: Boolean) {
        binding.edtPass.error = if (isNotValid) "Password kurang dari 6 karakter!" else null
    }
}
