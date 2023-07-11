package com.faezolfp.dripcontrol

import androidx.lifecycle.ViewModel
import com.faezolfp.dripcontrol.core.domain.model.Notifikasi
import com.faezolfp.dripcontrol.core.domain.usecase.UseCase

class MainViewModel(private val useCase: UseCase): ViewModel() {
    val getDataInfus = useCase.getDataInfus()
    val getDataMax = useCase.getDataInfusMax()
    fun saveNotifkasi(notifikasi: Notifikasi) = useCase.saveNotifikasi(notifikasi)
    val getNotifikasi = useCase.getNotifikasi()
}