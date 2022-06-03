package com.electrolux.imagesearchapp.ui.main.seachImage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.electrolux.imagesearchapp.network.repository.SearchImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchImageViewModel @Inject constructor(
    private val searchImageRepository: SearchImageRepository): ViewModel() {


    private val keywordMutableLiveData: MutableLiveData<String> = MutableLiveData()


    fun setKeyword(keyword:String){
        keywordMutableLiveData.value = keyword
    }

    var searchApiResponseLD =
        Transformations.switchMap(keywordMutableLiveData) { keyword ->
            searchImageRepository.getSearchResults(keyword).cachedIn(viewModelScope)
        }
}