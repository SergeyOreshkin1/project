package com.example.finalproject.presentation.characters.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.finalproject.domain.usecase.GetAllCharactersUseCase
import javax.inject.Inject

class CharactersViewModelFactory @Inject constructor(private val useCase: GetAllCharactersUseCase) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CharactersViewModel(useCase) as T
    }
}
