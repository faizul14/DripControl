package com.faezolfp.dripcontrol.core.domain.usecase

import androidx.lifecycle.LiveData
import com.faezolfp.dripcontrol.core.domain.model.Notifikasi
import com.faezolfp.dripcontrol.core.domain.model.Pasiens
import com.faezolfp.dripcontrol.core.domain.model.Users
import com.faezolfp.dripcontrol.core.domain.reposotory.IRepository

class UseCaseIteractor(private val repository: IRepository) : UseCase {
    override fun isLogin(): LiveData<Boolean> {
        return repository.isLogin()
    }

    override suspend fun login(status: Boolean) {
        repository.login(status)
    }

    override fun isIDUser(): LiveData<Int> {
        return repository.isIDUser()
    }

    override suspend fun saveIdUser(idUser: Int) {
        repository.saveIdUser(idUser)
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

    override fun registerUse(user: Users) {
        repository.registerUse(user)
    }

    override fun updateUser(user: Users) {
        repository.updateUser(user)
    }

    override fun getUseById(userId: Int): LiveData<Users> {
        return repository.getUseById(userId)
    }

    override fun loginUser(email: String, password: String): LiveData<Int> {
        return repository.loginUser(email, password)
    }

    override fun getUsernameById(UserId: Int): LiveData<String> {
        return repository.getUsernameById(UserId)
    }

    override fun addPasien(pasiens: Pasiens) {
        repository.addPasien(pasiens)
    }

    override fun deletePasien(pasiens: Pasiens) {
        repository.deletePasien(pasiens)
    }

    override fun getListPasiens(kamar: Int): LiveData<List<Pasiens>> {
        return repository.getListPasiens(kamar)
    }

    override fun saveNotifikasi(notifikasi: Notifikasi) {
        repository.saveNotifikasi(notifikasi)
    }

    override fun getNotifikasi(): LiveData<List<Notifikasi>> {
        return repository.getNotifikasi()
    }

    override fun setStatusInfus(status: String) {
        repository.setStatusInfus(status)
    }

    override fun getStatusInfus(): LiveData<String> {
        return repository.getStatusInfus()
    }
}
