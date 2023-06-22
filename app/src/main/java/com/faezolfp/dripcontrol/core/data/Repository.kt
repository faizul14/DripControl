package com.faezolfp.dripcontrol.core.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.faezolfp.dripcontrol.core.data.local.SettingPreferences
import com.faezolfp.dripcontrol.core.domain.reposotory.IRepository

class Repository(private val preferences: SettingPreferences, private val dataFirebase: Firebase) :
    IRepository {
    override fun isLogin(): LiveData<Boolean> {
        return preferences.getIsLogin().asLiveData()
    }

    override suspend fun login(status: Boolean) {
        preferences.saveIsLogin(status)
    }

    override suspend fun logout(status: Boolean) {
        preferences.saveIsLogin(status)
    }

    override fun setDataTpm(data: Int) {
        dataFirebase.setTpm(data)
    }

    override fun getDataTpm(): LiveData<Int> {
        return dataFirebase.getTpm()
    }

    override fun setDataInfus(data: Int) {
        dataFirebase.setDataInfus(data)
    }

    override fun getDataInfus(): LiveData<Int> {
        return dataFirebase.getDataInfus()
    }

    override fun setDataInfusMax(data: Int) {
        dataFirebase.setDataInfusMax(data)
    }

    override fun getDataInfusMax(): LiveData<Int> {
        return dataFirebase.getDataInfusMax()
    }

    companion object {
        @Volatile
        private var INSTANCE: Repository? = null

        fun getInstance(prev: SettingPreferences, firebase: Firebase): Repository =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Repository(prev, firebase)
            }
    }
}