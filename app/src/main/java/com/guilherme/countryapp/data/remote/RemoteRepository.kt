package com.guilherme.countryapp.data.remote

import com.guilherme.countryapp.data.mapper.toCountry
import com.guilherme.countryapp.domain.model.Country
import javax.inject.Inject

class RemoteRepository @Inject constructor(private val api: CountryService) {
    val TAG = "RemoteRepository"

    suspend fun refreshCountriesFromRemote(): List<Country> {
        val result = api.getAllCountries("cca3,name,capital,region,subregion,population,flags")
        return result.map { it.toCountry() }
    }
}
