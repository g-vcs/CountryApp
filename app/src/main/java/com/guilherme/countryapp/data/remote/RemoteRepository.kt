package com.guilherme.countryapp.data.remote

import com.guilherme.countryapp.data.mapper.toCountry
import com.guilherme.countryapp.domain.model.Country
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteRepository @Inject constructor(private val api: CountryService) {

    fun refreshCountriesFromRemote(): Flow<List<Country>> = flow {
        val result = api.getAllCountries()
        emit(result.map { it.toCountry() })
    }
}