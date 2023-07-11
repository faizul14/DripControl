package com.faezolfp.dripcontrol.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.faezolfp.dripcontrol.core.domain.ui.NotifikasiAdapter
import com.faezolfp.dripcontrol.core.utils.ViewModelFactory
import com.faezolfp.dripcontrol.databinding.FragmentNotificationsBinding

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null
    private lateinit var adapter: NotifikasiAdapter
    private lateinit var viewModel: NotificationsViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val factory = ViewModelFactory.getInstance(requireActivity().application)
        viewModel = ViewModelProvider(this, factory)[NotificationsViewModel::class.java]

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvListnotif.layoutManager = LinearLayoutManager(requireContext())
        viewModel.dataNotifikasi.observe(viewLifecycleOwner) {data->
            if (data != null){
                adapter = NotifikasiAdapter()
                adapter.setListNotofokasi(data)
                binding.rvListnotif.adapter = adapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
