package com.redpepper.shikiapp.data.remote

import com.redpepper.shikiapp.data.remote.models.anime.AnimeEntity
import com.redpepper.shikiapp.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

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