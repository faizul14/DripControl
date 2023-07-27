package com.faezolfp.dripcontrol.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.faezolfp.dripcontrol.core.utils.ViewModelFactory
import com.faezolfp.dripcontrol.databinding.FragmentHomeBinding
import com.faezolfp.dripcontrol.presentation.listpasien.ListPasienActivity
import com.faezolfp.dripcontrol.presentation.tracking.TrackingScreenActivity

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var viewModel: HomeViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val factory = ViewModelFactory.getInstance(requireActivity().application)
        viewModel = ViewModelProvider(requireActivity(), factory)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.cdKamar1.setOnClickListener {
            startActivity(
                Intent(requireActivity(), ListPasienActivity::class.java)
                .putExtra(ListPasienActivity.KAMARPASIEN, "1")
            )

        }
        binding.cdKamar2.setOnClickListener {
            startActivity(
                Intent(requireActivity(), ListPasienActivity::class.java)
                    .putExtra(ListPasienActivity.KAMARPASIEN, "2")
            )

        }
        binding.cdKamar3.setOnClickListener {
            startActivity(
                Intent(requireActivity(), ListPasienActivity::class.java)
                    .putExtra(ListPasienActivity.KAMARPASIEN, "3")
            )

        }
        binding.cdKamar4.setOnClickListener {
            startActivity(
                Intent(requireActivity(), ListPasienActivity::class.java)
                    .putExtra(ListPasienActivity.KAMARPASIEN, "4")
            )

        }
        observerViewModel()
    }
    private fun observerViewModel(){
        viewModel.idUser.observe(this){UserId->
            Log.d("TRACKING", "USERID ${UserId.toString()}")
            if (UserId != 0 || UserId != null){
                viewModel.getUsernameById(UserId).observe(this){data->
                    if (data!=null){
                        Log.d("TRACKING", "data ${data.toString()}")
                        binding.apply {
                            txtUsername.setText(data.toString())
                        }
                    }else{
                        binding.apply {
                            txtUsername.setText("Loading...")
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
