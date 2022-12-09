package com.redpepper.shikiapp.data.remote

import com.redpepper.shikiapp.data.remote.constants.AnimeInUserList
import com.redpepper.shikiapp.data.remote.constants.GenresModel
import com.redpepper.shikiapp.data.remote.constants.KindAndStatusModel
import retrofit2.Response
import retrofit2.http.GET

interface ConstantsInterface {

    @GET("api/constants/anime")
    suspend fun getKindAndStatusFilter():Response<KindAndStatusModel>

    @GET("api/constants/user_rate")
    suspend fun getAnimeInUserListFilter():Response<AnimeInUserList>

    @GET("/api/genres")
    suspend fun getGenresFilter(): Response<GenresModel>
}