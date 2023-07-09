package com.faezolfp.dripcontrol.presentation.addpasien

import androidx.lifecycle.ViewModel
import com.faezolfp.dripcontrol.core.domain.model.Pasiens
import com.faezolfp.dripcontrol.core.domain.usecase.UseCase

class InserPasienViewModel(val useCase: UseCase): ViewModel() {
    fun saveDataPasien(pasiens: Pasiens) = useCase.addPasien(pasiens)
}