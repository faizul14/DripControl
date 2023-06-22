package com.faezolfp.dripcontrol.core.domain.usecase

import androidx.lifecycle.LiveData
import com.faezolfp.dripcontrol.core.domain.reposotory.IRepository

class UseCaseIteractor(private val repository: IRepository) : UseCase {
    override fun isLogin(): LiveData<Boolean> {
        return repository.isLogin()
    }

    override suspend fun login(status: Boolean) {
        repository.login(status)
    }

    override suspend fun logout(status: Boolean) {
        repository.logout(status)
    }

    override fun setDataTpm(data: Int) {
        repository.setDataTpm(data)
    }

    override fun getDataTpm(): LiveData<Int> {
        return repository.getDataTpm()
    }

    override fun setDataInfus(data: Int) {
        repository.setDataInfus(data)
    }

    override fun getDataInfus(): LiveData<Int> {
        return repository.getDataInfus()
    }

    override fun setDataInfusMax(data: Int) {
        repository.setDataInfusMax(data)
    }

    override fun getDataInfusMax(): LiveData<Int> {
        return repository.getDataInfusMax()
    }
}
