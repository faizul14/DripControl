package com.faezolfp.dripcontrol.core.domain.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.faezolfp.dripcontrol.R
import com.faezolfp.dripcontrol.core.domain.model.Pasiens
import com.faezolfp.dripcontrol.databinding.ItemListBinding
import com.faezolfp.dripcontrol.presentation.tracking.TrackingScreenActivity

class ListPasienAdapter : RecyclerView.Adapter<ListPasienAdapter.ViewHolder>() {

    private val listPasiens = ArrayList<Pasiens>()
    private lateinit var dataInfus: String

    fun setDataPasien(data: List<Pasiens>) {
        listPasiens.clear()
        listPasiens.addAll(data)
        notifyDataSetChanged()
    }

    fun setDataInfusPasien(data: String) {
        dataInfus = data
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Pasiens, dataInfus: String) {
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
                txtStatusinfus.text = "$dataInfus%"
            }

            itemView.setOnClickListener {
                val dataMove = data
                val move = Intent(itemView.context, TrackingScreenActivity::class.java)
                move.putExtra(TrackingScreenActivity.DATA_MOVE, dataMove)
                itemView.context.startActivity(move)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = listPasiens.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listPasiens[position], dataInfus)
    }

    private var onSwipeListener: OnSwipeListener? = null

    fun setOnSwipeListener(listener: OnSwipeListener) {
        this.onSwipeListener = listener
    }

    interface OnSwipeListener {
        fun onSwipe(position: Int, data: Pasiens)
    }

    inner class SwipeCallback :
        ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position = viewHolder.adapterPosition
            val data = listPasiens[position]
            onSwipeListener?.onSwipe(position, data)
            notifyItemRemoved(position)
        }
    }

    fun deleteItem(position: Int) {
        listPasiens.removeAt(position)
        notifyItemRemoved(position)
    }


    private val itemTouchHelper = ItemTouchHelper(SwipeCallback())

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }
}
