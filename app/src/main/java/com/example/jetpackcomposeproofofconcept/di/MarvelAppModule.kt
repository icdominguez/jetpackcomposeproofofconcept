package com.example.jetpackcomposeproofofconcept.di

import android.content.Context
import androidx.room.Room
import com.example.jetpackcomposeproofofconcept.domain.CharacterDao
import com.example.jetpackcomposeproofofconcept.data.MarvelApiRepositoryImpl
import com.example.jetpackcomposeproofofconcept.data.MarvelApiService
import com.example.jetpackcomposeproofofconcept.data.MarvelDatabaseRepositoryImpl
import com.example.jetpackcomposeproofofconcept.domain.Constants
import com.example.jetpackcomposeproofofconcept.domain.MarvelApiRepository
import com.example.jetpackcomposeproofofconcept.domain.MarvelDatabase
import com.example.jetpackcomposeproofofconcept.domain.MarvelDatabaseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MarvelAppModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Singleton
    @Provides
    fun provideApiClient(retrofit: Retrofit): MarvelApiService {
        return retrofit.create(MarvelApiService::class.java)
    }

    @Provides
    fun provideMarvelApiRepository(marvelApiService: MarvelApiService): MarvelApiRepository = MarvelApiRepositoryImpl(marvelApiService)

    @Singleton
    @Provides
    fun provideMarvelDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        MarvelDatabase::class.java,
        "marvel_database"
    ).build()

    @Singleton
    @Provides
    fun provideCharacterDao(db: MarvelDatabase) = db.characterDao()

    @Provides
    fun provideMarvelDatabaseRepository(characterDao: CharacterDao): MarvelDatabaseRepository = MarvelDatabaseRepositoryImpl(characterDao)

}
