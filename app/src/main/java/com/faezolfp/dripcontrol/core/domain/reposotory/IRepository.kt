package com.faezolfp.dripcontrol.core.domain.reposotory

import androidx.lifecycle.LiveData

interface IRepository {
    fun isLogin(): LiveData<Boolean>

    suspend fun login(status: Boolean)
    suspend fun logout(status: Boolean)
    fun setDataTpm(data: Int)
    fun getDataTpm(): LiveData<Int>
}