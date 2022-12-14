package ru.kudagonish.bankai.ui.searchAnime

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.kudagonish.bankai.data.remote.models.anime.AnimeEntityItem
import ru.kudagonish.bankai.data.repository.AnimeRepository
import javax.inject.Inject

@HiltViewModel
class SearchAnimeViewModel @Inject constructor(private val repository: AnimeRepository) :
    ViewModel() {

    lateinit var searchResult: LiveData<PagingData<AnimeEntityItem>>

    fun getSortedByRankAnime() {
        searchResult = repository.getAnimeLiveData()
            .cachedIn(viewModelScope)
    }

    fun getSearchAnimeByName(name: String) {
        searchResult = repository.getSearchByNameLiveData(name)
            .cachedIn(viewModelScope)
    }
}