package com.faezolfp.dripcontrol.ui.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.faezolfp.dripcontrol.MainActivity2
import com.faezolfp.dripcontrol.R
import com.faezolfp.dripcontrol.core.utils.ViewModelFactory
import com.faezolfp.dripcontrol.databinding.FragmentProfileBinding
import com.faezolfp.dripcontrol.presentation.editprofile.EditProfileActivity
import com.faezolfp.dripcontrol.presentation.login.LoginActivity


class ProfileFragment : Fragment(){
    private lateinit var _binding: FragmentProfileBinding
    private val binding get() = _binding!!
    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val factory = ViewModelFactory.getInstance(requireActivity().application)
        viewModel = ViewModelProvider(requireActivity(), factory).get(ProfileViewModel::class.java)

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        displayButton()
    }

    private fun displayButton() {
        binding.btnLogout.setOnClickListener{
            viewModel.logout()
            startActivity(Intent(requireActivity(), LoginActivity::class.java))
            requireActivity().finish()
        }

        binding.editProfile.setOnClickListener{
            startActivity(Intent(requireActivity(), EditProfileActivity::class.java))
        }
    }

}