package ru.kudagonish.bankai.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import ru.kudagonish.bankai.data.remote.models.anime.AnimeEntity
import ru.kudagonish.bankai.utils.Constants

interface AnimeInterface {

    @GET("animes?limit=${Constants.PAGE_SIZE}")
    suspend fun getAnimeList(
        @Query("order") orderBy: String,
        @Query("page") page: Int
    ): Response<AnimeEntity>

    @GET("animes?limit=${Constants.PAGE_SIZE}")
    suspend fun searchAnimeByName(
        @Query("order") orderBy: String,
        @Query("page") page: Int,
        @Query("search") name: String
    ): Response<AnimeEntity>
}