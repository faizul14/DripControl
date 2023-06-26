package com.faezolfp.dripcontrol.presentation.register

import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.faezolfp.dripcontrol.R
import com.faezolfp.dripcontrol.core.domain.model.Users
import com.faezolfp.dripcontrol.core.utils.ViewModelFactory
import com.faezolfp.dripcontrol.databinding.ActivityRegisterBinding
import com.faezolfp.dripcontrol.presentation.login.LoginViewModel
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Observable

class RegisterActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModel: RegisterViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val factory = ViewModelFactory.getInstance(application)
        viewModel = ViewModelProvider(this, factory).get(RegisterViewModel::class.java)


        displayButton()
        displayValidasi()
    }

    private fun displayValidasi() {
        val nameStream = RxTextView.textChanges(binding.edtName).skipInitialValue().map { name ->
            name.length < 2
        }
        nameStream.subscribe {
            edtNameAllert(it)
        }
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

        val passwordConfirmationStream =
            Observable.merge(
                RxTextView.textChanges(binding.edtPass).map { password ->
                    password.toString() != binding.edtCompass.text.toString()
                },
                RxTextView.textChanges(binding.edtCompass).map { confirmPassword ->
                    confirmPassword.toString() != binding.edtPass.text.toString()
                }
            )
        val subscribe = passwordConfirmationStream.subscribe {
            edtPassConfirmAllert(it)
        }

//        val invalidFieldsStream = Observable.combineLatest(
//            emailStream,
//            passtream,
//            passwordConfirmationStream,
//            Function3 { emailInvalid: Boolean, passwordInvalid: Boolean, passwordConfirmationInvalid: Boolean ->
//                !emailInvalid && !passwordInvalid && !passwordConfirmationInvalid
//            }
//        )
//
//        invalidFieldsStream.subscribe { isValid ->
//            if (isValid) {
//                binding.btnRegister.isEnabled = true
//                binding.btnRegister.setBackgroundColor(ContextCompat.getColor(this, R.color.purple_500))
//            } else {
//                binding.btnRegister.isEnabled = false
//                binding.btnRegister.setBackgroundColor(ContextCompat.getColor(this, android.R.color.darker_gray))
//            }
//        }
    }

    private fun register(){
        var username: String? = null
        var fulname: String? = null
        var email: String? = null
        var password: String? = null

        binding.apply {
            username = edtName.text.toString()
            fulname = edtName.text.toString()
            email = edtEmail.text.toString()
            password = edtPass.text.toString()
        }

        if (username != null && fulname != null && email != null && password != null){
            val data = Users(
                username = username,
                fullname = fulname,
                email = email,
                pasword = password
            )
            viewModel.register(data)
            Toast.makeText(this, "Register Berhasil!!", Toast.LENGTH_SHORT).show()
            finish()
        }else{
            Toast.makeText(this, "Register Gagal!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun edtNameAllert(isNotValid: Boolean) {
        binding.edtName.error = if (isNotValid) "Nama masih kosong!" else null
    }

    private fun edtEmailAllert(isNotValid: Boolean) {
        binding.edtEmail.error = if (isNotValid) "Email tidak valid" else null
    }

    private fun edtPassAllert(isNotValid: Boolean) {
        binding.edtPass.error = if (isNotValid) "Password kurang dari 6 karakter!" else null
    }

    private fun edtPassConfirmAllert(isNotValid: Boolean) {
        binding.edtCompass.error = if (isNotValid) "Password belum cocok!" else null
    }

    private fun displayButton() {
        binding.txtLogin.setOnClickListener(this)
        binding.btnRegister.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.txt_login -> {
                finish()
            }
            R.id.btn_register -> {
                register()
            }
        }
    }
}
