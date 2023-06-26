package com.faezolfp.dripcontrol.core.domain.usecase

import androidx.lifecycle.LiveData
import com.faezolfp.dripcontrol.core.domain.model.Users

interface UseCase {
    fun isLogin(): LiveData<Boolean>
    suspend fun login(status: Boolean)
    suspend fun logout(status: Boolean)
    fun setDataTpm(data: Int)
    fun getDataTpm(): LiveData<Int>
    fun setDataInfus(data: Int)
    fun getDataInfus(): LiveData<Int>
    fun setDataInfusMax(data: Int)
    fun getDataInfusMax(): LiveData<Int>
    fun registerUse(user: Users)
}
