package com.guilherme.countryapp.domain.usecase

import com.guilherme.countryapp.domain.model.Country
import com.guilherme.countryapp.domain.repository.ICountryRepository
import javax.inject.Inject

class InsertCountryUseCase @Inject constructor(
    private val repository: ICountryRepository
) {
    suspend operator fun invoke(country: Country) = repository.insertCountry(country)
}