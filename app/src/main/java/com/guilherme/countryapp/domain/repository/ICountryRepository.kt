package com.guilherme.countryapp.domain.repository

import com.guilherme.countryapp.domain.model.Country
import kotlinx.coroutines.flow.Flow

interface ICountryRepository {
    fun getCountries(): Flow<List<Country>?>
    fun getCountryByName(country: String): Flow<Country?>
    suspend fun insertCountry(country:Country)
    suspend fun deleteCountry(country: Country)
    suspend fun updateCountry(country: Country)
    suspend fun refreshCountriesFromRemote(): Flow<List<Country>>
}
