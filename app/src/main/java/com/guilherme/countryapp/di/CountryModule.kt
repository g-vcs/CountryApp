package com.guilherme.countryapp.di

import android.content.Context
import androidx.room.Room
import com.guilherme.countryapp.data.local.AppDatabase
import com.guilherme.countryapp.data.local.CountryDao
import com.guilherme.countryapp.data.local.LocalRepository
import com.guilherme.countryapp.data.repository.CountryRepositoryImpl
import com.guilherme.countryapp.domain.repository.ICountryRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CountryModule {

    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext.applicationContext,
            AppDatabase::class.java,
            "countries_database"
        ).build()
    }

    @Provides
    fun providesCountryDao(database: AppDatabase): CountryDao {
        return database.countryDao()
    }

}
