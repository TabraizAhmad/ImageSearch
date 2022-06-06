package com.electrolux.imagesearchapp.ui.main.seachImage

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.electrolux.imagesearchapp.network.repository.SearchImageRepository
import dagger.assisted.Assisted
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchImageViewModel @Inject constructor(
    private val state: SavedStateHandle,
    private val searchImageRepository: SearchImageRepository): ViewModel() {
    private val currentQuery = state.getLiveData(CURRENT_QUERY, DEFAULT_QUERY)


    fun setKeyword(keyword:String){
        currentQuery.value = keyword
    }

    var searchApiResponseLD =
        Transformations.switchMap(currentQuery) { keyword ->
            searchImageRepository.getSearchResults(keyword).cachedIn(viewModelScope)
        }

    companion object {
        private const val CURRENT_QUERY = "current_query"
        private const val DEFAULT_QUERY = "Electrolux"
    }
}