package com.icdominguez.network.di

import com.icdominguez.network.MarvelApiRepositoryImpl
import com.icdominguez.network.api.Constants
import com.icdominguez.network.api.MarvelApiRepository
import com.icdominguez.network.api.MarvelApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(
            GsonConverterFactory.create()).build()
    }

    @Singleton
    @Provides
    fun provideApiClient(retrofit: Retrofit): MarvelApiService {
        return retrofit.create(MarvelApiService::class.java)
    }

    @Provides
    fun provideMarvelApiRepository(marvelApiService: MarvelApiService): MarvelApiRepository = MarvelApiRepositoryImpl(marvelApiService)
}