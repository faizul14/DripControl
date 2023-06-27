package com.faezolfp.dripcontrol.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.faezolfp.dripcontrol.core.domain.usecase.UseCase
import kotlinx.coroutines.launch

class LoginViewModel(private val useCase: UseCase) : ViewModel() {
    fun login() {
        viewModelScope.launch {
            useCase.login(true)
        }
    }

    fun login(email: String, password: String) = useCase.loginUser(email, password)
}
