package com.faezolfp.dripcontrol.presentation.listpasien

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.faezolfp.dripcontrol.R
import com.faezolfp.dripcontrol.core.domain.ui.ListPasienAdapter
import com.faezolfp.dripcontrol.core.utils.ViewModelFactory
import com.faezolfp.dripcontrol.databinding.ActivityListPasienBinding
import com.faezolfp.dripcontrol.presentation.addpasien.InsertPasienActivity

class ListPasienActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityListPasienBinding
    private lateinit var viewModel: ListPasienViewModel
    private var kamarPasie: String? = null
    private lateinit var adapter: ListPasienAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListPasienBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        kamarPasie = intent.getStringExtra(KAMARPASIEN)
        val factory = ViewModelFactory.getInstance(application)
        viewModel = ViewModelProvider(this, factory)[ListPasienViewModel::class.java]

        buttonDisplay()
        display()
    }

//    override fun onResume() {
//        super.onResume()
//        binding.imgBlank.visibility = View.VISIBLE
//        binding.rvListpasien.visibility = View.GONE
//    }

    private fun display() {
        binding.rvListpasien.layoutManager = LinearLayoutManager(this)
        kamarPasie?.let {
            viewModel.dataPasien(it.toInt()).observe(this) { dataPasien ->
                if (dataPasien.isNotEmpty()){
                    adapter = ListPasienAdapter()
                    adapter.setDataPasien(dataPasien)
                    binding.rvListpasien.adapter = adapter
                    binding.imgBlank.visibility = View.GONE
                    binding.rvListpasien.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun buttonDisplay() {
        binding.btnAdd.setOnClickListener(this)
        binding.btnBack1.setOnClickListener(this)
    }

    companion object {
        const val KAMARPASIEN = "kamar_pasien"
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btn_add -> {
                val move = Intent(this, InsertPasienActivity::class.java)
                move.putExtra(InsertPasienActivity.KAMARPASIEN, kamarPasie)
                startActivity(move)
            }
            R.id.btn_back1 -> {
                finish()
            }
        }
    }
}