package com.guilherme.countryapp.data.remote

import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface CountryService {

    @GET("all")
    suspend fun getAllCountries(): List<CountryDto>
}