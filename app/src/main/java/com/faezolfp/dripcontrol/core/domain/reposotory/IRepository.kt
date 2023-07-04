package com.faezolfp.dripcontrol.core.domain.reposotory

import androidx.lifecycle.LiveData
import com.faezolfp.dripcontrol.core.domain.model.Users

interface IRepository {
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
    fun loginUser(email: String, password: String): LiveData<Int>

    fun getUsernameById(UserId: Int): LiveData<String>

}
