package com.guilherme.countryapp.data.repository

import com.guilherme.countryapp.domain.model.Country
import com.guilherme.countryapp.domain.repository.ICountryRepository
import kotlinx.coroutines.flow.Flow

class CountryRepositoryImpl: ICountryRepository {
    override fun getCountries(): Flow<List<Country>> {
        TODO("Not yet implemented")
    }

    override suspend fun getCountryByName(country: String): Country? {
        TODO("Not yet implemented")
    }
}