package com.faezolfp.dripcontrol.core.domain.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.faezolfp.dripcontrol.core.domain.model.Notifikasi
import com.faezolfp.dripcontrol.databinding.ItemNotifikasiBinding
import com.faezolfp.dripcontrol.presentation.tracking.TrackingScreenActivity

class NotifikasiAdapter : RecyclerView.Adapter<NotifikasiAdapter.ViewHolder>() {

    private val listNotifikasi = ArrayList<Notifikasi>()
    fun setListNotofokasi(data: List<Notifikasi>) {
        listNotifikasi.clear()
        listNotifikasi.addAll(data)
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: ItemNotifikasiBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Notifikasi) {
            val message = when (data.kamar) {
                "1" -> {
                    "SEGERA GANTI INFUS UNTUK PASIEN B2 KAMAR RAWAT I"
                }
                "2" -> {
                    "SEGERA GANTI INFUS UNTUK PASIEN B2 KAMAR RAWAT II"
                }
                "3" -> {
                    "SEGERA GANTI INFUS UNTUK PASIEN B2 KAMAR RAWAT III"
                }
                else -> {
                    "SEGERA GANTI INFUS UNTUK PASIEN B2 KAMAR RAWAT IV"
                }
            }
            binding.txtMesage.text = message

            itemView.setOnClickListener {
                itemView.context.startActivity(
                    Intent(
                        itemView.context,
                        TrackingScreenActivity::class.java
                    )
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemNotifikasiBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = listNotifikasi.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listNotifikasi[position])
    }
}