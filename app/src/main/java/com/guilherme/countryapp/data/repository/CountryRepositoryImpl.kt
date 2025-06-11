package com.guilherme.countryapp.data.repository

import android.util.Log
import com.guilherme.countryapp.data.local.LocalRepository
import com.guilherme.countryapp.data.remote.RemoteRepository
import com.guilherme.countryapp.domain.model.Country
import com.guilherme.countryapp.domain.repository.ICountryRepository
import com.guilherme.countryapp.utils.MockUtil.mockCountryBR
import com.guilherme.countryapp.utils.MockUtil.mockCountryPT
import com.guilherme.countryapp.utils.MockUtil.mockCountryES
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CountryRepositoryImpl @Inject constructor(
    private val localRepository: LocalRepository,
    private val remoteRepository: RemoteRepository
) : ICountryRepository {
    override fun getCountries(): Flow<List<Country>> {
        return localRepository.getCountries()
    }

    override fun getCountryByName(country: String): Flow<Country> {
        return localRepository.getCountryByName(country)
    }

    override fun getFavoriteCountries(): Flow<List<Country>> {
        return localRepository.getFavoriteCountry()
    }

    override suspend fun insertCountry(country: Country) {
        localRepository.insertCountry(country)
    }

    override suspend fun deleteCountry(country: Country) {
        localRepository.deleteCountry(country)
    }

    override suspend fun updateCountry(country: Country) {
        localRepository.updateCountry(country)
    }

    override suspend fun refreshCountriesFromRemote() {
        val remoteCountries = remoteRepository.refreshCountriesFromRemote()
        Log.d("NETWORK", "remote countries: $remoteCountries")
        localRepository.insertCountry(mockCountryBR)
        localRepository.insertCountry(mockCountryPT)
        localRepository.insertCountry(mockCountryES)
        remoteCountries.forEach { localRepository.insertCountry(it) }

    }


}
