package com.faezolfp.dripcontrol.presentation.tracking

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.faezolfp.dripcontrol.R
import com.faezolfp.dripcontrol.core.utils.ViewModelFactory
import com.faezolfp.dripcontrol.databinding.ActivityTrackingScreenBinding

class TrackingScreenActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityTrackingScreenBinding
    private lateinit var viewModel: TrackingViewModel
    private var dataSave = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTrackingScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val factory = ViewModelFactory.getInstance(application)
        viewModel = ViewModelProvider(this, factory).get(TrackingViewModel::class.java)

        setDisplay()
    }

    private fun setDisplay() {
        displayButton()
        displayObserveViewModel()
    }


    private fun displayButton() {
        binding.buttonMinus.setOnClickListener(this)
        binding.buttonPlus.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.buttonPlus -> {
                if (dataSave < 150) {
                    dataSave += 10
                    viewModel.setDataTpm(dataSave)
                } else {
                    Toast.makeText(this, "Batas Maksimum", Toast.LENGTH_SHORT).show()
                }
            }
            R.id.buttonMinus -> {
                if (dataSave > 0){
                    dataSave -= 10
                    viewModel.setDataTpm(dataSave)
                }else{
                    Toast.makeText(this, "Batas Minimum", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }

    private fun displayObserveViewModel() {
        viewModel.getDataTpm.observe(this) { data ->
            if (data != null) {
                dataSave = data.toInt()
                binding.buttonMinus.isEnabled = data.toInt() != 0
                binding.progressBar1.progress = data.toInt()
                binding.txtTpm.text = "$data\nTPM"
            } else {
                dataSave = 0
            }
        }
    }


}