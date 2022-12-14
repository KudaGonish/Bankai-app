package ru.kudagonish.bankai.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import ru.kudagonish.bankai.data.remote.AnimeInterface
import ru.kudagonish.bankai.utils.Constants
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AnimeRepository @Inject constructor(private val animeInterface: AnimeInterface) {

    //возможно переделать для вывода разной сортировки
    fun getAnimeLiveData(/*orderBy: String*/) = Pager(
        pagingSourceFactory = {
            SearchAnimePagingSource(
                "ranked",
                animeInterface
            )
        },
        config = PagingConfig(pageSize = Constants.PAGE_SIZE)
    ).liveData

    fun getSearchByNameLiveData(name: String) = Pager(
        pagingSourceFactory = {
            SearchAnimePagingSource(
                "ranked",
                name,
                animeInterface
            )
        },
        config = PagingConfig(pageSize = Constants.PAGE_SIZE)
    ).liveData
}