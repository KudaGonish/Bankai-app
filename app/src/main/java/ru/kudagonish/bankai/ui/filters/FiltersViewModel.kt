package ru.kudagonish.bankai.ui.filters

import androidx.lifecycle.ViewModel
import ru.kudagonish.bankai.data.repository.ConstantsRepository
import javax.inject.Inject

class FiltersViewModel @Inject constructor(private val constantsRepository: ConstantsRepository) :
    ViewModel() {

    suspend fun getKindAndStatusFilters() = constantsRepository.getKindAndStatusFilter()

    suspend fun getGenresFilters() = constantsRepository.getGenresFilter()

    suspend fun getUserListFilters() = constantsRepository.getUserListFilters()

}