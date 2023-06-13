package com.faezolfp.dripcontrol.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.faezolfp.dripcontrol.core.domain.usecase.UseCase
import kotlinx.coroutines.launch

class ProfileViewModel(private val useCase: UseCase): ViewModel() {
    fun logout(){
        viewModelScope.launch {
            useCase.logout(false)
        }
    }
}