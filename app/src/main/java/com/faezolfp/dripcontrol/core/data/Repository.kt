package com.faezolfp.dripcontrol.core.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.faezolfp.dripcontrol.core.data.local.SettingPreferences
import com.faezolfp.dripcontrol.core.domain.reposotory.IRepository

class Repository(private val preferences: SettingPreferences): IRepository {
    override fun isLogin(): LiveData<Boolean> {
        return preferences.getIsLogin().asLiveData()
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