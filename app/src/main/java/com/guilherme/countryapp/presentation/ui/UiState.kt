package com.guilherme.countryapp.presentation.ui

import com.guilherme.countryapp.domain.model.Country

sealed class UiState {
    data class NavigateToCountryDetails(val country: Country): UiState()
}