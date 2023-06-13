package com.faezolfp.dripcontrol.core.utils

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.faezolfp.dripcontrol.SplashViewModel
import com.faezolfp.dripcontrol.core.di.Injection
import com.faezolfp.dripcontrol.core.domain.usecase.UseCase
import com.faezolfp.dripcontrol.presentation.login.LoginViewModel
import com.faezolfp.dripcontrol.ui.profile.ProfileViewModel

class ViewModelFactory private constructor(private val useCase: UseCase) : ViewModelProvider.NewInstanceFactory() {
    companion object {
        const val TAG = "ViewModelFactory"

        @Volatile
        private var INCSTANCE: ViewModelFactory? = null

        fun getInstance(application: Application): ViewModelFactory = INCSTANCE ?: synchronized(this) {
            INCSTANCE ?: ViewModelFactory(Injection.provideUseCase(application))
        }
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(ListTerkiniViewModel::class.java)) {
//            return ListTerkiniViewModel(useCase) as T
//        }
//        if (modelClass.isAssignableFrom(ListDirasakanViewModel::class.java)) {
//            return ListDirasakanViewModel(useCase) as T
//        }
//        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
//            return HomeViewModel(useCase) as T
//        }
        if (modelClass.isAssignableFrom(SplashViewModel::class.java)) {
            return SplashViewModel(useCase) as T
        }
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(useCase) as T
        }
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            return ProfileViewModel(useCase) as T
        }

        throw IllegalArgumentException("Message ${modelClass.name}")

    }
}
