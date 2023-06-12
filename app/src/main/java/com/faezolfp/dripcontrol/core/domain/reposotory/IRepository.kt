package com.faezolfp.dripcontrol.core.domain.reposotory

import androidx.lifecycle.LiveData

interface IRepository {
    fun isLogin(): LiveData<Boolean>
}