package com.electrolux.imagesearchapp.di

import com.electrolux.imagesearchapp.network.remote.FlickrApiService
import com.electrolux.imagesearchapp.network.repository.SearchImageRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun provideArticleRepository(
        service: FlickrApiService
    ): SearchImageRepository {
        return SearchImageRepository(service)
    }

}