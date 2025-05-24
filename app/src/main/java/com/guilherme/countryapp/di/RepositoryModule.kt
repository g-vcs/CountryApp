package com.guilherme.countryapp.di

import com.guilherme.countryapp.data.repository.CountryRepositoryImpl
import com.guilherme.countryapp.domain.repository.ICountryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCountryRepositoy(
        repositoryImpl: CountryRepositoryImpl
    ): ICountryRepository
}
