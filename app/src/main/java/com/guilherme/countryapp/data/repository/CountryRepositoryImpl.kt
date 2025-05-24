package com.guilherme.countryapp.data.repository

import com.guilherme.countryapp.data.local.CountryDao
import com.guilherme.countryapp.data.mapper.toCountry
import com.guilherme.countryapp.data.mapper.toCountryEntity
import com.guilherme.countryapp.domain.model.Country
import com.guilherme.countryapp.domain.repository.ICountryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CountryRepositoryImpl @Inject constructor(
    private val countryDao: CountryDao
) : ICountryRepository {
    override fun getCountries(): Flow<List<Country>> =
        countryDao.getAllCountries().map { list -> list.map { it.toCountry() } }

    override fun getCountryByName(country: String): Flow<Country?> =
        countryDao.getCountry(country).map { it.toCountry() }

    override suspend fun insertCountry(country: Country) =
        countryDao.insertCountry(country.toCountryEntity())

    override suspend fun deleteCountry(country: Country) =
        countryDao.deleteCountry(country.toCountryEntity())

    override suspend fun updateCountry(country: Country) =
        countryDao.updateCountry(country.toCountryEntity())

}
