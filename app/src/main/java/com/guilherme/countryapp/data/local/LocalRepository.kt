package com.guilherme.countryapp.data.local

import com.guilherme.countryapp.data.mapper.toCountry
import com.guilherme.countryapp.data.mapper.toCountryEntity
import com.guilherme.countryapp.domain.model.Country
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalRepository @Inject constructor(
    private val countryDao: CountryDao
) {
    fun getCountries(): Flow<List<Country>?> =
        countryDao.getAllCountries().map { list -> list.map { it.toCountry() } }

    fun getCountryByName(country: String): Flow<Country?> =
        countryDao.getCountry(country).map { it?.toCountry() }

    suspend fun insertCountry(country: Country) =
        countryDao.insertCountry(country.toCountryEntity())

    suspend fun deleteCountry(country: Country) =
        countryDao.deleteCountry(country.toCountryEntity())

    suspend fun updateCountry(country: Country) =
        countryDao.updateCountry(country.toCountryEntity())

}
