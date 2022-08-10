package com.example.finalproject.presentation.locations.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.finalproject.domain.usecase.GetAllLocationsUseCase
import javax.inject.Inject

class LocationsViewModelFactory @Inject constructor(private val useCase: GetAllLocationsUseCase) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LocationsViewModel(useCase) as T
    }
}
