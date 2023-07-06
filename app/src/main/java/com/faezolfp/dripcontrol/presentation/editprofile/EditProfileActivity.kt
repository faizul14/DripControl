package com.faezolfp.dripcontrol.presentation.editprofile

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.faezolfp.dripcontrol.R
import com.faezolfp.dripcontrol.core.domain.model.Users
import com.faezolfp.dripcontrol.core.utils.ViewModelFactory
import com.faezolfp.dripcontrol.databinding.ActivityEditProfileBinding

class EditProfileActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityEditProfileBinding
    private lateinit var viewModel: EditProfilViewModel
    private var userId : String? = null
    private var dataUser: Users? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        val factory = ViewModelFactory.getInstance(application)
        viewModel = ViewModelProvider(this, factory)[EditProfilViewModel::class.java]

        userId = intent.getStringExtra(IDUSER)
        if (userId != null){
            viewModel.getUserById(userId!!.toInt()).observe(this){ data->
                dataUser = data
                binding.apply {
                    edtUsername.setText(data.username)
                    edtFullname.setText(data.fullname)
                    edtEmail.setText(data.email)
                }
            }
        }

        setDisplay()
    }

    private fun setDisplay() {
        binding.btnBack.setOnClickListener(this)
        binding.btnEdit.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btn_back -> {
                finish()
            }
            R.id.btn_edit -> {
                var fulname: String = ""
                var username: String = ""
                var email: String = ""

                binding.apply {
                    username = edtUsername.text.toString()
                    fulname = edtFullname.text.toString()
                    email = edtEmail.text.toString()
                }
                val data = Users(
                    id = dataUser?.id!!,
                    username = username,
                    fullname = fulname,
                    email = email,
                    pasword = dataUser?.pasword
                )

                viewModel.update(data)
                finish()
            }
        }
    }

    companion object{
        const val IDUSER = "id_user"
    }
}
