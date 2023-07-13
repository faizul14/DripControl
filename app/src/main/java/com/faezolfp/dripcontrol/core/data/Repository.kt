package com.faezolfp.dripcontrol.core.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import com.faezolfp.dripcontrol.core.data.local.SettingPreferences
import com.faezolfp.dripcontrol.core.data.local.UserDao
import com.faezolfp.dripcontrol.core.domain.model.Notifikasi
import com.faezolfp.dripcontrol.core.domain.model.Pasiens
import com.faezolfp.dripcontrol.core.domain.model.Users
import com.faezolfp.dripcontrol.core.domain.reposotory.IRepository
import com.faezolfp.dripcontrol.core.utils.DataMapper
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class Repository(
    private val preferences: SettingPreferences,
    private val dataFirebase: Firebase,
    private val userDao: UserDao
) : IRepository {

    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    override fun isLogin(): LiveData<Boolean> {
        return preferences.getIsLogin().asLiveData()
    }

    override suspend fun login(status: Boolean) {
        preferences.saveIsLogin(status)
    }

    override fun isIDUser(): LiveData<Int> {
        return preferences.getIdUser().asLiveData()
    }

    override suspend fun saveIdUser(idUser: Int) {
        preferences.saveIdUser(idUser)
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
        executorService.execute {
            userDao.userRegister(DataMapper.dataMapFromModelToEntity(user))
        }
    }

    override fun updateUser(user: Users) {
        executorService.execute {
            userDao.userUpdate(DataMapper.dataMapFromModelToEntity(user))
        }
    }

    override fun getUseById(userId: Int): LiveData<Users> {
        return userDao.getUserById(userId).map { userEntity ->
            DataMapper.dataMapFromEntityToModel(userEntity)
        }
    }

    override fun loginUser(email: String, password: String): LiveData<Int> {
        return userDao.login(email, password).apply {
            try {
                map { data ->
                    if (data != null) data else -1

                }
            } catch (e: Exception) {
                return 0 as LiveData<Int>
            }
        }
    }

    override fun getUsernameById(UserId: Int): LiveData<String> {
        return userDao.getUsernameById(UserId)
    }

    override fun addPasien(pasiens: Pasiens) {
        executorService.execute {
            userDao.addPasien(DataMapper.dataMapFromModelToPasienEntity(pasiens))
        }
    }

    override fun getListPasiens(kamar: Int): LiveData<List<Pasiens>> {
        return userDao.getListPasien(kamar).map {
            DataMapper.dataMapFromListPasienEntityToListPasienModel(it)
        }
    }

    override fun saveNotifikasi(notifikasi: Notifikasi) {
        executorService.execute {
            userDao.saveNotifikasi(DataMapper.dataMapFromModelToEntityNotifikasi(notifikasi))
        }
    }

    override fun getNotifikasi(): LiveData<List<Notifikasi>> {
        return userDao.getNotifikasi().map {
            DataMapper.dataMapFromEntityToModelNotifikasi(it)
        }
    }

    override fun setStatusInfus(status: String) {
        dataFirebase.setStatusInfus(status)
    }

    override fun getStatusInfus(): LiveData<String> {
        return dataFirebase.getStatusInfus()
    }

    companion object {
        @Volatile
        private var INSTANCE: Repository? = null

        fun getInstance(
            prev: SettingPreferences, firebase: Firebase, userDao: UserDao
        ): Repository = INSTANCE ?: synchronized(this) {
            INSTANCE ?: Repository(prev, firebase, userDao)
        }
    }
}
