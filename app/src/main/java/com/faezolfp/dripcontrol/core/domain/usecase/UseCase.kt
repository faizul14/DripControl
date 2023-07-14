package com.faezolfp.dripcontrol.core.domain.usecase

import androidx.lifecycle.LiveData
import com.faezolfp.dripcontrol.core.domain.model.Notifikasi
import com.faezolfp.dripcontrol.core.domain.model.Pasiens
import com.faezolfp.dripcontrol.core.domain.model.Users

interface UseCase {
    fun isLogin(): LiveData<Boolean>
    suspend fun login(status: Boolean)
    fun isIDUser(): LiveData<Int>
    suspend fun saveIdUser(idUser: Int)
    suspend fun logout(status: Boolean)
    fun setDataTpm(data: Int)
    fun getDataTpm(): LiveData<Int>
    fun setDataInfus(data: Int)
    fun getDataInfus(): LiveData<Int>
    fun setDataInfusMax(data: Int)
    fun getDataInfusMax(): LiveData<Int>
    fun registerUse(user: Users)
    fun updateUser(user: Users)
    fun getUseById(userId: Int): LiveData<Users>
    fun loginUser(email: String, password: String): LiveData<Int>
    fun getUsernameById(UserId: Int): LiveData<String>
    fun addPasien(pasiens: Pasiens)
    fun deletePasien(pasiens: Pasiens)
    fun getListPasiens(kamar: Int): LiveData<List<Pasiens>>
    fun saveNotifikasi(notifikasi: Notifikasi)
    fun getNotifikasi(): LiveData<List<Notifikasi>>
    fun setStatusInfus(status: String)
    fun getStatusInfus(): LiveData<String>
}
