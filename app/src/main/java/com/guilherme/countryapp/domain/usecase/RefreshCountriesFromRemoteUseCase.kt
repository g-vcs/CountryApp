package com.guilherme.countryapp.domain.usecase

import com.guilherme.countryapp.domain.repository.ICountryRepository
import javax.inject.Inject

class RefreshCountriesFromRemoteUseCase @Inject constructor(
    private val repository: ICountryRepository
) {
    suspend operator fun invoke() = repository.refreshCountriesFromRemote()
}