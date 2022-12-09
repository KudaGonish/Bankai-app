package com.redpepper.shikiapp.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.redpepper.shikiapp.data.remote.AnimeInterface
import com.redpepper.shikiapp.data.remote.models.anime.AnimeEntityItem
import com.redpepper.shikiapp.utils.Constants


class SearchAnimePagingSource(
    private val orderBy: String, private val animeInterface: AnimeInterface
) : PagingSource<Int, AnimeEntityItem>() {

    private lateinit var _name: String

    constructor(orderBy: String, name: String, animeInterface: AnimeInterface) : this(
        orderBy,
        animeInterface
    ) {
        _name = name
    }


    override fun getRefreshKey(state: PagingState<Int, AnimeEntityItem>): Int? {

        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AnimeEntityItem> {

        val page = params.key ?: Constants.INITIAL_PAGE

        return try {

            val data =
                if (::_name.isInitialized) animeInterface.searchAnimeByName(orderBy = orderBy, name = _name, page = page)
                else animeInterface.getAnimeList(orderBy = orderBy, page = page)

            LoadResult.Page(
                data = data.body()!!,
                prevKey = if (page == Constants.INITIAL_PAGE) null else page - 1,
                nextKey = if (data.body()?.isEmpty()!!) null else page + 1
            )

        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }
}