package com.guilherme.countryapp.domain.usecase

import com.guilherme.countryapp.domain.model.Country
import com.guilherme.countryapp.domain.repository.ICountryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteCountriesUseCase @Inject constructor(
    private val repository: ICountryRepository
) {
    operator fun invoke(): Flow<List<Country>> = repository.getFavoriteCountries()
}