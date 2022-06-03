package com.electrolux.imagesearchapp.network.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.electrolux.imagesearchapp.network.model.Photo
import retrofit2.HttpException
import java.io.IOException

private const val FLIKR_STARTING_PAGE_INDEX = 1
class FlickrPagingSource(private val flickrApiService: FlickrApiService,
                         private val query: String):  PagingSource<Int, Photo>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
        val position = params.key ?: FLIKR_STARTING_PAGE_INDEX

        return try {
            val response = flickrApiService.searchImages(query, position, params.loadSize)
            if(response.isSuccessful){
                var photos: ArrayList<Photo> = arrayListOf()
                response.body()?.photos?.photo?.let {
                    photos = it
                }
                LoadResult.Page(
                    data = photos,
                    prevKey = if (position == FLIKR_STARTING_PAGE_INDEX) null else position - 1,
                    nextKey = if (photos.isEmpty()) null else position + 1
                )
            }else{
                LoadResult.Error(HttpException(response))
            }

        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Photo>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }
}