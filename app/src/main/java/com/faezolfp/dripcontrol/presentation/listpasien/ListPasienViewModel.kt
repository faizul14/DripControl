package com.faezolfp.dripcontrol.presentation.listpasien

import androidx.lifecycle.ViewModel
import com.faezolfp.dripcontrol.core.domain.model.Pasiens
import com.faezolfp.dripcontrol.core.domain.usecase.UseCase

class ListPasienViewModel(private val useCase: UseCase): ViewModel() {
    fun dataPasien(kamar: Int) = useCase.getListPasiens(kamar)
    fun deletePasien(pasiens: Pasiens) = useCase.deletePasien(pasiens)
    fun addPasien(pasiens: Pasiens) = useCase.addPasien(pasiens)
}