package com.icdominguez.database.di

import android.content.Context
import androidx.room.Room
import com.icdominguez.database.api.CharacterDao
import com.icdominguez.database.api.MarvelDatabaseRepository
import com.icdominguez.database.data.MarvelDatabase
import com.icdominguez.database.presentation.MarvelDatabaseRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
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
