package com.faezolfp.dripcontrol.presentation.editprofile

import androidx.lifecycle.ViewModel
import com.faezolfp.dripcontrol.core.domain.model.Users
import com.faezolfp.dripcontrol.core.domain.usecase.UseCase

class EditProfilViewModel(private val useCase: UseCase): ViewModel() {
    fun getUserById(userId: Int) = useCase.getUseById(userId)
    fun update(users: Users) = useCase.updateUser(users)
}