package com.redpepper.shikiapp.data.repository

import com.redpepper.shikiapp.data.remote.ConstantsInterface
import com.redpepper.shikiapp.data.remote.constants.KindAndStatusModel
import javax.inject.Inject

class ConstantsRepository @Inject constructor(private val constantsInterface : ConstantsInterface) {

    suspend fun getKindAndStatusFilter() =  constantsInterface.getKindAndStatusFilter()

    suspend fun getUserListFilters() = constantsInterface.getAnimeInUserListFilter()

    suspend fun getGenresFilter() = constantsInterface.getGenresFilter()
}