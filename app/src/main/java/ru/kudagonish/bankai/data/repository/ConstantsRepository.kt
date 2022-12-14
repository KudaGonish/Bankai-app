package ru.kudagonish.bankai.data.repository

import ru.kudagonish.bankai.data.remote.ConstantsInterface
import javax.inject.Inject

class ConstantsRepository @Inject constructor(private val constantsInterface: ConstantsInterface) {

    suspend fun getKindAndStatusFilter() = constantsInterface.getKindAndStatusFilter()

    suspend fun getUserListFilters() = constantsInterface.getAnimeInUserListFilter()

    suspend fun getGenresFilter() = constantsInterface.getGenresFilter()
}