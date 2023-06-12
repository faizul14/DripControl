package com.faezolfp.dripcontrol

import androidx.lifecycle.ViewModel
import com.faezolfp.dripcontrol.core.domain.usecase.UseCase

class SplashViewModel(private val useCase: UseCase): ViewModel() {
    val getIsLogin = useCase.isLogin()
}