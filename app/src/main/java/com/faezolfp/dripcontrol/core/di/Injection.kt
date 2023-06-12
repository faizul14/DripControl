package com.faezolfp.dripcontrol.core.di

import android.app.Application
import com.faezolfp.dripcontrol.core.data.Repository
import com.faezolfp.dripcontrol.core.data.local.SettingPreferences
import com.faezolfp.dripcontrol.core.data.local.dataStore
import com.faezolfp.dripcontrol.core.domain.reposotory.IRepository
import com.faezolfp.dripcontrol.core.domain.usecase.UseCase
import com.faezolfp.dripcontrol.core.domain.usecase.UseCaseIteractor

object Injection {

    private fun provideRepostitory(application: Application): IRepository {
//        val remote = RemoteDataSource.getInstance(ApiConfig.provideApiService())
        val prev = SettingPreferences.getInstance(application.dataStore)
        return Repository.getInstance(prev)
    }

    fun provideUseCase(application: Application): UseCase {
        val repository = provideRepostitory(application)
        return UseCaseIteractor(repository)
    }
}