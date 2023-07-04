package com.faezolfp.dripcontrol.presentation.register

import androidx.lifecycle.ViewModel
import com.faezolfp.dripcontrol.core.domain.model.Users
import com.faezolfp.dripcontrol.core.domain.usecase.UseCase

class RegisterViewModel(private val useCase: UseCase): ViewModel() {
    fun register(users: Users){
        useCase.registerUse(users)
    }
}