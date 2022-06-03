package com.electrolux.imagesearchapp.network.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.electrolux.imagesearchapp.network.remote.FlickrApiService
import com.electrolux.imagesearchapp.network.remote.FlickrPagingSource
import javax.inject.Inject

class SearchImageRepository @Inject constructor(private val apiService: FlickrApiService) {

    fun getSearchResults(query: String) =
        Pager(
            config = PagingConfig(
                initialLoadSize = 27,
                pageSize = 27,
                maxSize = 108,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { FlickrPagingSource(apiService, query) }
        ).liveData
}