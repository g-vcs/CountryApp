package com.guilherme.countryapp.presentation.ui.events

import com.guilherme.countryapp.domain.model.Country

sealed class UiCountryListEvent {
    data class CountryClick(val country: Country): UiCountryListEvent()
    data class AddToFavorite(val country: Country) : UiCountryListEvent()
}