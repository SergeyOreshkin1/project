package com.example.finalproject.presentation.characters.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.finalproject.domain.usecase.GetAllCharactersUseCase
import com.example.finalproject.presentation.characters.paging.CharactersPagingSource
import javax.inject.Inject

class CharactersViewModel @Inject
constructor(
    private val useCase: GetAllCharactersUseCase
) : ViewModel() {

    val charactersList = Pager(PagingConfig(pageSize = 1)) {
        CharactersPagingSource(useCase)

    }.flow.cachedIn(viewModelScope)
}
