package com.faezolfp.dripcontrol.core.data

import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.faezolfp.dripcontrol.core.data.local.SettingPreferences
import com.faezolfp.dripcontrol.core.domain.reposotory.IRepository
import kotlinx.coroutines.coroutineScope

class Repository(private val preferences: SettingPreferences): IRepository {
    override fun isLogin(): LiveData<Boolean> {
        return preferences.getIsLogin().asLiveData()
    }

    override suspend fun login(status: Boolean) {
            preferences.saveIsLogin(status)
    }

    override suspend fun logout(status: Boolean) {
        preferences.saveIsLogin(status)
    }

    companion object{
        @Volatile
        private var INSTANCE: Repository? = null

        fun getInstance(prev: SettingPreferences): Repository =
            INSTANCE ?: synchronized(this){
                INSTANCE ?: Repository(prev)
            }
    }
}