package ru.kudagonish.bankai.data.remote

import retrofit2.Response
import retrofit2.http.GET
import ru.kudagonish.bankai.data.remote.constants.AnimeInUserList
import ru.kudagonish.bankai.data.remote.constants.GenresModel
import ru.kudagonish.bankai.data.remote.constants.KindAndStatusModel

interface ConstantsInterface {

    @GET("constants/anime")
    suspend fun getKindAndStatusFilter(): Response<KindAndStatusModel>

    @GET("constants/user_rate")
    suspend fun getAnimeInUserListFilter(): Response<AnimeInUserList>

    @GET("genres")
    suspend fun getGenresFilter(): Response<GenresModel>
}