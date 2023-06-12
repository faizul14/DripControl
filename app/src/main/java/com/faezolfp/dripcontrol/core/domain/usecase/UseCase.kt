package com.faezolfp.dripcontrol.core.domain.usecase

import androidx.lifecycle.LiveData

interface UseCase {
    fun isLogin(): LiveData<Boolean>
}