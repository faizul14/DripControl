package com.faezolfp.dripcontrol.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.faezolfp.dripcontrol.core.domain.usecase.UseCase

class NotificationsViewModel(private val useCase: UseCase): ViewModel() {

    val dataNotifikasi = useCase.getNotifikasi()
}
