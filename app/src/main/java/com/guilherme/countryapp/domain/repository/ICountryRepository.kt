package com.guilherme.countryapp.domain.repository

import com.guilherme.countryapp.domain.model.Country
import kotlinx.coroutines.flow.Flow

interface ICountryRepository {
    fun getCountries(): Flow<List<Country>>
    suspend fun getCountryByName(country: String): Country?
}