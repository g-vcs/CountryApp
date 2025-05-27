package com.guilherme.countryapp.presentation.ui

import com.guilherme.countryapp.domain.model.Country

sealed class UiEvent {
    data class CountryClick(val country: Country): UiEvent()
    data class AddToFavorite(val country: Country) : UiEvent()
}
