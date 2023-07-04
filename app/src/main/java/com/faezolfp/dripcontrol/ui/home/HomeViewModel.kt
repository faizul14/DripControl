package com.faezolfp.dripcontrol.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.faezolfp.dripcontrol.core.domain.usecase.UseCase

class HomeViewModel(private val useCase: UseCase) : ViewModel() {

    val idUser = useCase.isIDUser()
    fun getUsernameById(UserId: Int) = useCase.getUsernameById(UserId)
}
