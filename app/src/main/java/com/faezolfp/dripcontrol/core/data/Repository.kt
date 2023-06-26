package com.faezolfp.dripcontrol.core.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.faezolfp.dripcontrol.core.data.local.SettingPreferences
import com.faezolfp.dripcontrol.core.data.local.UserDao
import com.faezolfp.dripcontrol.core.domain.model.Users
import com.faezolfp.dripcontrol.core.domain.reposotory.IRepository
import com.faezolfp.dripcontrol.core.utils.DataMapper
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class Repository(private val preferences: SettingPreferences, private val dataFirebase: Firebase, private val userDao: UserDao) :
    IRepository {

    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

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

    override fun registerUse(user: Users) {
        executorService.execute{
            userDao.userRegister(DataMapper.dataMapFromModelToEntity(user))
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: Repository? = null

        fun getInstance(prev: SettingPreferences, firebase: Firebase, userDao: UserDao): Repository =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Repository(prev, firebase, userDao)
            }
    }
}
