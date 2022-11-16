package com.redpepper.shikiapp.ui.searchAnime

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.redpepper.shikiapp.data.remote.models.AnimeEntityItem
import com.redpepper.shikiapp.data.repository.AnimeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchAnimeViewModel @Inject constructor(private val repository: AnimeRepository) :
    ViewModel() {

    lateinit var searchResult: LiveData<PagingData<AnimeEntityItem>>

    fun getSortedByRankAnime() {
        searchResult = repository.getAnimeLiveData()
            .cachedIn(viewModelScope)
    }
}