package com.example.finalproject.presentation.episodes.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.finalproject.domain.usecase.GetAllEpisodesUseCase
import com.example.finalproject.presentation.episodes.paging.EpisodesPagingSource
import javax.inject.Inject

class EpisodesViewModel @Inject
constructor(
    private val useCase: GetAllEpisodesUseCase
) : ViewModel() {

    val episodesList = Pager(PagingConfig(pageSize = 1)) {
        EpisodesPagingSource(useCase)

    }.flow.cachedIn(viewModelScope)
}
