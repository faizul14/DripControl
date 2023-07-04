package com.faezolfp.dripcontrol.core.di

import android.app.Application
import com.faezolfp.dripcontrol.core.data.Firebase
import com.faezolfp.dripcontrol.core.data.Repository
import com.faezolfp.dripcontrol.core.data.local.DatabaseRoom
import com.faezolfp.dripcontrol.core.data.local.SettingPreferences
import com.faezolfp.dripcontrol.core.data.local.dataStore
import com.faezolfp.dripcontrol.core.domain.reposotory.IRepository
import com.faezolfp.dripcontrol.core.domain.usecase.UseCase
import com.faezolfp.dripcontrol.core.domain.usecase.UseCaseIteractor

object Injection {

    private fun provideRepository(application: Application): IRepository {
        val prev = SettingPreferences.getInstance(application.dataStore)
        val firebase = Firebase.getInstance()
        val db = DatabaseRoom.getDatabase(application)
        val userDao = db.userDao()
        return Repository.getInstance(prev, firebase = firebase, userDao)
    }

    fun provideUseCase(application: Application): UseCase {
        val repository = provideRepository(application)
        return UseCaseIteractor(repository)
    }
}
