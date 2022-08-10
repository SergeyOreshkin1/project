package com.example.finalproject.presentation.episodes.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.finalproject.domain.usecase.GetAllEpisodesUseCase
import javax.inject.Inject

class EpisodesViewModelFactory @Inject constructor(private val useCase: GetAllEpisodesUseCase) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return EpisodesViewModel(useCase) as T
    }
}
