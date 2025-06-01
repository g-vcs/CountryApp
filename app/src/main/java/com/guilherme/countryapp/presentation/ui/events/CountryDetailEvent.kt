package com.guilherme.countryapp.presentation.ui.events

import com.guilherme.countryapp.domain.model.Country

sealed class CountryDetailEvent {
    data class AddToFavorite(val country: Country?): CountryDetailEvent()
}