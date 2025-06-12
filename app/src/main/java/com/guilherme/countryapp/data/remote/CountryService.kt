package com.guilherme.countryapp.data.remote

import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface CountryService {

    @GET("all")
    suspend fun getAllCountries(
        @Query("fields") fields: String
    ): List<CountryDto>
}