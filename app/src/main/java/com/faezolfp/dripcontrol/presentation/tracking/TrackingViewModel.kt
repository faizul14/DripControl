package com.faezolfp.dripcontrol.presentation.tracking

import androidx.lifecycle.ViewModel
import com.faezolfp.dripcontrol.core.domain.usecase.UseCase

class TrackingViewModel(private val useCase: UseCase) : ViewModel() {

    fun setDataTpm(isValue: Int) {
        useCase.setDataTpm(isValue)
    }

    val getDataTpm = useCase.getDataTpm()
    fun setDataInfus(isValue: Int) {
        useCase.setDataInfus(isValue)
    }

    val getDataInfus = useCase.getDataInfus()
    fun setDataInfusMax(isValue: Int) {
        useCase.setDataInfus(isValue)
    }

    val getDataInfusMax = useCase.getDataInfusMax()
}
