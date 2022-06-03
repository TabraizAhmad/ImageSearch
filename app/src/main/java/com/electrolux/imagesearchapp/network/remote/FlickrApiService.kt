package com.electrolux.imagesearchapp.network.remote

import com.electrolux.imagesearchapp.network.model.FlickrImageSearchData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrApiService {

    @GET("services/rest")
    suspend fun searchImages(
        @Query("tags") keyword: String,
        @Query("page") page: Int?,
        @Query("per_page") resultsPerPage: Int?
    ):  Response<FlickrImageSearchData>

}