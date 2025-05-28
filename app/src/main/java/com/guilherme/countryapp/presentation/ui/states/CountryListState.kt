package com.guilherme.countryapp.presentation.ui.states

import com.guilherme.countryapp.domain.model.Country

data class CountryListState(
    val countries: List<Country> = emptyList(),
    val favorites: List<Country> = emptyList(),
    val isShowingFavorites: Boolean = false,
    val selectedCountry: Country? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)