package ru.kudagonish.bankai.data.remote

import retrofit2.Response
import retrofit2.http.GET
import ru.kudagonish.bankai.data.remote.constants.AnimeInUserList
import ru.kudagonish.bankai.data.remote.constants.GenresModel
import ru.kudagonish.bankai.data.remote.constants.KindAndStatusModel

interface ConstantsInterface {

    @GET("api/constants/anime")
    suspend fun getKindAndStatusFilter(): Response<KindAndStatusModel>

    @GET("api/constants/user_rate")
    suspend fun getAnimeInUserListFilter(): Response<AnimeInUserList>

    @GET("/api/genres")
    suspend fun getGenresFilter(): Response<GenresModel>
}