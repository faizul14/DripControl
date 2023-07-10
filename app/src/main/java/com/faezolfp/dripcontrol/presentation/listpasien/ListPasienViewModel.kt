package com.faezolfp.dripcontrol.presentation.listpasien

import androidx.lifecycle.ViewModel
import com.faezolfp.dripcontrol.core.domain.usecase.UseCase

class ListPasienViewModel(private val useCase: UseCase): ViewModel() {
    fun dataPasien(kamar: Int) = useCase.getListPasiens(kamar)
}