package com.faezolfp.dripcontrol.presentation.tracking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.faezolfp.dripcontrol.R
import com.faezolfp.dripcontrol.databinding.ActivityTrackingScreenBinding

class TrackingScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTrackingScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTrackingScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}