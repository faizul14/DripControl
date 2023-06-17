package com.faezolfp.dripcontrol.presentation.tracking

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.faezolfp.dripcontrol.core.domain.usecase.UseCase

class TrackingViewModel(private val useCase: UseCase): ViewModel() {
    private val _dataTpm = MutableLiveData<Int>()
    val dataTpm : LiveData<Int> = _dataTpm

    fun setDataTpm(isValue: Int){
        _dataTpm.value = isValue

    }
}