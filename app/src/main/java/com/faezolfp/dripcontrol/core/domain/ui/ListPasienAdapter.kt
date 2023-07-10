package com.faezolfp.dripcontrol.core.domain.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.faezolfp.dripcontrol.core.domain.model.Pasiens
import com.faezolfp.dripcontrol.databinding.ItemListBinding

class ListPasienAdapter : RecyclerView.Adapter<ListPasienAdapter.ViewHolder>() {

    private val listPasiens = ArrayList<Pasiens>()
    fun setDataGempaTerkini(data: List<Pasiens>) {
        listPasiens.clear()
        listPasiens.addAll(data)
        notifyDataSetChanged()
    }

    class ViewHolder(binding: ItemListBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Pasiens){

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