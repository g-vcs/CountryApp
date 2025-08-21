package com.guilherme.countryapp.presentation.ui.events

sealed class CountryListEvent {
    data class ShowFavoriteCountries(val show: Boolean) : CountryListEvent()
    data class SearchQueryChanged(val query: String) : CountryListEvent()
}