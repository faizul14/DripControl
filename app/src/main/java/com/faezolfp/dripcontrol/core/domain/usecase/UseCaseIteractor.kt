package com.faezolfp.dripcontrol.core.domain.usecase

import androidx.lifecycle.LiveData
import com.faezolfp.dripcontrol.core.data.Repository
import com.faezolfp.dripcontrol.core.domain.reposotory.IRepository

class UseCaseIteractor(private val repository: IRepository): UseCase {
    override fun isLogin(): LiveData<Boolean> {
       return repository.isLogin()
    }

    override suspend fun login(status: Boolean) {
        repository.login(status)
    }

    override suspend fun logout(status: Boolean) {
        repository.logout(status)
    }
}