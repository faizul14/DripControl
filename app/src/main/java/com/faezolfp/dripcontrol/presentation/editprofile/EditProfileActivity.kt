package com.faezolfp.dripcontrol.presentation.editprofile

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.faezolfp.dripcontrol.R
import com.faezolfp.dripcontrol.databinding.ActivityEditProfileBinding

class EditProfileActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityEditProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        setDisplay()
    }

    private fun setDisplay() {
        binding.btnBack.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btn_back -> {
                finish()
            }
        }
    }
}
