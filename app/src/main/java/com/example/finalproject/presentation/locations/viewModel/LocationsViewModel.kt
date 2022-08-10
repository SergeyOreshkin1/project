package com.example.finalproject.presentation.locations.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.finalproject.domain.usecase.GetAllLocationsUseCase
import com.example.finalproject.presentation.locations.paging.LocationsPagingSource
import javax.inject.Inject

class LocationsViewModel @Inject
constructor(
    private val useCase: GetAllLocationsUseCase
) : ViewModel() {

    val locationsList = Pager(PagingConfig(pageSize = 1)) {
        LocationsPagingSource(useCase)

    }.flow.cachedIn(viewModelScope)
}
