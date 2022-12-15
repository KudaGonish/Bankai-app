package ru.kudagonish.bankai.ui.filters

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Response
import ru.kudagonish.bankai.data.remote.constants.AnimeInUserList
import ru.kudagonish.bankai.data.remote.constants.KindAndStatusModel
import ru.kudagonish.bankai.data.repository.ConstantsRepository
import javax.inject.Inject
@HiltViewModel
class FiltersViewModel @Inject constructor(private val constantsRepository: ConstantsRepository) :
    ViewModel() {

    lateinit var kindAndStatusFilter : KindAndStatusModel
    lateinit var userListFilter : AnimeInUserList

    suspend fun getKindAndStatusFilters(){
        kindAndStatusFilter = constantsRepository.getKindAndStatusFilter().body()!!
    }

    suspend fun getGenresFilters() = constantsRepository.getGenresFilter()

    suspend fun getUserListFilters() {
        userListFilter = constantsRepository.getUserListFilters().body()!!
    }

}