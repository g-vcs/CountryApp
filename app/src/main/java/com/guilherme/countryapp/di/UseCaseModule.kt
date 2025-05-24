package com.guilherme.countryapp.di

import com.guilherme.countryapp.domain.usecase.CountryUseCases
import com.guilherme.countryapp.domain.usecase.DeleteCountryUseCase
import com.guilherme.countryapp.domain.usecase.GetAllCountriesUseCase
import com.guilherme.countryapp.domain.usecase.GetCountryByNameUseCase
import com.guilherme.countryapp.domain.usecase.InsertCountryUseCase
import com.guilherme.countryapp.domain.usecase.RefreshCountriesFromRemoteUseCase
import com.guilherme.countryapp.domain.usecase.UpdateCountryUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideCountryUseCases(
        insertCountryUseCase: InsertCountryUseCase,
        updateCountryUseCase: UpdateCountryUseCase,
        deleteCountryUseCase: DeleteCountryUseCase,
        getAllCountriesUseCase: GetAllCountriesUseCase,
        getCountryByNameUseCase: GetCountryByNameUseCase,
        refreshCountriesFromRemoteUseCase: RefreshCountriesFromRemoteUseCase
    ): CountryUseCases {
        return CountryUseCases(
            insertCountryUseCase = insertCountryUseCase,
            updateCountryUseCase = updateCountryUseCase,
            deleteCountryUseCase = deleteCountryUseCase,
            getAllCountriesUseCase = getAllCountriesUseCase,
            getCountryByNameUseCase = getCountryByNameUseCase,
            refreshCountriesFromRemoteUseCase = refreshCountriesFromRemoteUseCase
        )
    }
}