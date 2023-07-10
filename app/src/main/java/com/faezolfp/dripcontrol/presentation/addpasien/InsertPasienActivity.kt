package com.faezolfp.dripcontrol.presentation.addpasien

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.faezolfp.dripcontrol.R
import com.faezolfp.dripcontrol.core.domain.model.Pasiens
import com.faezolfp.dripcontrol.core.utils.ViewModelFactory
import com.faezolfp.dripcontrol.databinding.ActivityInsertPasienBinding

class InsertPasienActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityInsertPasienBinding
    private lateinit var viewModel: InserPasienViewModel
    private var kamarPasien: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInsertPasienBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        kamarPasien = intent.getStringExtra(KAMARPASIEN)

        val factory = ViewModelFactory.getInstance(application)
        viewModel = ViewModelProvider(this, factory)[InserPasienViewModel::class.java]

        btnDisplay()
    }

    private fun btnDisplay() {
        binding.btnSimpan.setOnClickListener(this)
        binding.btnBack.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.btn_simpan -> {
                display()
            }
            R.id.btn_back -> {
                finish()
            }
        }
    }

    private fun display(){
        var nama: String? = null
        var umur: String? = null
        var beratbadan: String? = null
        var banyakCairanInfus: String? = null
        var lamanPemberianInfus: String? = null
        var tetesanPerMenit: String? = null
        var kamar: Int? = kamarPasien?.toInt() ?: null

        binding.apply {
            nama = edtNama.text.toString()
            umur = edtUmur.text.toString()
            beratbadan = edtBrtbdn.text.toString()
            banyakCairanInfus = edtBanyakcairaninfus.text.toString()
            lamanPemberianInfus = edtLamapemberianinfus.text.toString()
            tetesanPerMenit = edtTetesanpermenit.text.toString()
        }
        if ( nama != null && umur != null && beratbadan != null &&
            banyakCairanInfus != null && lamanPemberianInfus != null &&
            tetesanPerMenit != null && kamar != null){
            val data = Pasiens(
                nama = nama,
                umur = umur,
                brtbadan = beratbadan,
                banyakcairaninfus = banyakCairanInfus,
                lamapemberianinfus = lamanPemberianInfus,
                tetsanpermenit = tetesanPerMenit,
                kamar = kamar,
            )
            viewModel.saveDataPasien(data)
            Toast.makeText(this, "Data berhasil di simpan!!!", Toast.LENGTH_SHORT).show()
        }
    }

    companion object{
        const val KAMARPASIEN = "kamar_pasien"
    }
}