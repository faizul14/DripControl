package com.faezolfp.dripcontrol.core.domain.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.faezolfp.dripcontrol.R
import com.faezolfp.dripcontrol.core.domain.model.Pasiens
import com.faezolfp.dripcontrol.databinding.ItemListBinding

class ListPasienAdapter : RecyclerView.Adapter<ListPasienAdapter.ViewHolder>() {

    private val listPasiens = ArrayList<Pasiens>()
    fun setDataPasien(data: List<Pasiens>) {
        listPasiens.clear()
        listPasiens.addAll(data)
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Pasiens) {
            var dataBB: String? = null
            when (data.kamar) {
                1 -> {
                    dataBB = "B1"
                    binding.materialCardView.setCardBackgroundColor(itemView.resources.getColor(R.color.colorshape1))
                }
                2 -> {
                    dataBB = "B2"
                    binding.materialCardView.setCardBackgroundColor(itemView.resources.getColor(R.color.colorshape2))
                }
                3 -> {
                    dataBB = "B3"
                    binding.materialCardView.setCardBackgroundColor(itemView.resources.getColor(R.color.colorshape3))
                }
                else -> {
                    dataBB = "B4"
                    binding.materialCardView.setCardBackgroundColor(itemView.resources.getColor(R.color.colorshape4))
                }
            }
            binding.apply {
                txtNama.text = data.nama
                txtUmur.text = "${data.umur} Tahun"
                txtBeratbadan.text = "${data.brtbadan} KG"
                txtBbb.text = dataBB
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = listPasiens.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listPasiens[position])
    }
}