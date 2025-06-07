package com.guilherme.countryapp.data.remote

import com.guilherme.countryapp.data.mapper.toCountry
import com.guilherme.countryapp.domain.model.Country
import javax.inject.Inject

class RemoteRepository @Inject constructor(private val api: CountryService) {
    suspend fun refreshCountriesFromRemote(): List<Country> {
        val result = api.getAllCountries()
        return result.map { it.toCountry() }
    }
}
