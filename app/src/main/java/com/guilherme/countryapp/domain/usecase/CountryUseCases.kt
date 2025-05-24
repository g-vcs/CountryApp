package com.guilherme.countryapp.domain.usecase

data class CountryUseCases (
    val insertCountryUseCase: InsertCountryUseCase,
    val updateCountryUseCase: UpdateCountryUseCase,
    val deleteCountryUseCase: DeleteCountryUseCase,
    val getAllCountriesUseCase: GetAllCountriesUseCase,
    val getCountryByNameUseCase: GetCountryByNameUseCase,
    val refreshCountriesFromRemoteUseCase: RefreshCountriesFromRemoteUseCase,
)