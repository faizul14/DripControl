package com.faezolfp.dripcontrol.presentation.login

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.faezolfp.dripcontrol.MainActivity2
import com.faezolfp.dripcontrol.R
import com.faezolfp.dripcontrol.core.utils.ViewModelFactory
import com.faezolfp.dripcontrol.databinding.ActivityLoginBinding
import com.faezolfp.dripcontrol.presentation.register.RegisterActivity
import com.jakewharton.rxbinding2.widget.RxTextView
import com.shashank.sony.fancytoastlib.FancyToast
import io.reactivex.Observable

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
        setValidasiDisplay()
    }

    @SuppressLint("CheckResult")
    private fun setValidasiDisplay() {
        val emailStream = RxTextView.textChanges(binding.edtEmail).skipInitialValue().map { email ->
            !Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }
        emailStream.subscribe {
            edtEmailAllert(it)
        }
        val passStream = RxTextView.textChanges(binding.edtPass).skipInitialValue().map {
            it.length < 6
        }
        passStream.subscribe {
            edtPassAllert(it)
        }
        val emailpassConfirmation = Observable.combineLatest(
            emailStream, passStream
        ) { emailIsValid: Boolean, passwordIsValid: Boolean ->
            !emailIsValid && !passwordIsValid
        }
        emailpassConfirmation.subscribe { isValid ->
            if (isValid) {
                binding.btnRegister.isEnabled = true
                binding.btnRegister.setBackgroundDrawable(
                    ContextCompat.getDrawable(
                        this, R.drawable.btn_reg_log
                    )
                )
            } else {
                binding.btnRegister.isEnabled = false
                binding.btnRegister.setBackgroundDrawable(
                    ContextCompat.getDrawable(
                        this, R.drawable.btn_reg_log_disabled
                    )
                )
            }
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
                login()
            }
        }
    }

    private fun login() {
        var email: String? = null
        var password: String? = null
        binding.apply {
            email = edtEmail.text.toString()
            password = edtPass.text.toString()
        }
        if (email != null && password != null) {
            viewModel.login(email!!, password!!).observe(this) { data ->
                if (data != 0 && data != null) {
                    FancyToast.makeText(this, "Login Berhasil!",FancyToast.LENGTH_LONG, FancyToast.SUCCESS, false).show()
                    viewModel.saveIdUser(data.toInt())
                    viewModel.login()
                    startActivity(Intent(this, MainActivity2::class.java))
                    finish()
                } else {
                    FancyToast.makeText(this, "Email atau Password Masih Salah!!",FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show()

                }
            }
        } else {
            FancyToast.makeText(this, "Email atau Password Kosong!!",FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show()
        }
    }

    private fun edtEmailAllert(isNotValid: Boolean) {
        binding.edtEmail.error = if (isNotValid) "Email tidak valid" else null
    }

    private fun edtPassAllert(isNotValid: Boolean) {
        binding.edtPass.error = if (isNotValid) "Password kurang dari 6 karakter!" else null
    }
}
