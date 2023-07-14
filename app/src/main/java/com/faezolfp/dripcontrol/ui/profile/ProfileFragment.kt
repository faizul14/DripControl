package com.faezolfp.dripcontrol.ui.profile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.faezolfp.dripcontrol.core.utils.ViewModelFactory
import com.faezolfp.dripcontrol.databinding.FragmentProfileBinding
import com.faezolfp.dripcontrol.presentation.editprofile.EditProfileActivity
import com.faezolfp.dripcontrol.presentation.login.LoginActivity
import com.shashank.sony.fancytoastlib.FancyToast

class ProfileFragment : Fragment() {
    private lateinit var _binding: FragmentProfileBinding
    private val binding get() = _binding!!
    private lateinit var viewModel: ProfileViewModel
    private  var idUserFromObserver: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val factory = ViewModelFactory.getInstance(requireActivity().application)
        viewModel = ViewModelProvider(requireActivity(), factory).get(ProfileViewModel::class.java)

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        displayObserver()
        displayButton()
    }

    private fun displayObserver() {
        viewModel.idUser.observe(this){data->
            idUserFromObserver = data
            if (data != 0 || data != null){
                viewModel.getUsernameById(data).observe(this){data->
                    Log.d("TRACKING", "data ${data.toString()}")
                    binding.apply {
                        textView5.setText(data.toString())
                    }
                }
            }
        }
    }

    private fun displayButton() {
        binding.btnLogout.setOnClickListener {
            viewModel.logout()
            viewModel.saveIdUser(0)
            startActivity(Intent(requireActivity(), LoginActivity::class.java))
            requireActivity().finish()
        }

        binding.editProfile.setOnClickListener {
//            startActivity(Intent(requireActivity(), EditProfileActivity::class.java))
            if (idUserFromObserver !=  0){
                val move = Intent(requireContext(), EditProfileActivity::class.java)
                move.putExtra(EditProfileActivity.IDUSER, idUserFromObserver.toString())
                startActivity(move)
            }
        }
    }
}
