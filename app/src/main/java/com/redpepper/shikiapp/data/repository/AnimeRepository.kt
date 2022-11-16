package com.redpepper.shikiapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.redpepper.shikiapp.data.remote.AnimeInterface
import com.redpepper.shikiapp.utils.Constants
import javax.inject.Inject
import javax.inject.Singleton
@Singleton
class AnimeRepository @Inject constructor(private val animeInterface: AnimeInterface) {

    //возможно переделать для вывода разной сортировки
    fun getAnimeLiveData(/*orderBy: String*/) = Pager(
        pagingSourceFactory = { SearchAnimePagingSource("ranked", animeInterface) },
        config = PagingConfig(pageSize = Constants.PAGE_SIZE)
    ).liveData
}