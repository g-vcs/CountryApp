package com.guilherme.countryapp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.guilherme.countryapp.domain.model.Country
import com.guilherme.countryapp.domain.repository.ICountryRepository
import com.guilherme.countryapp.presentation.ui.events.CountryDetailEvent
import com.guilherme.countryapp.presentation.ui.states.CountryListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryDetailViewModel @Inject constructor(
    private val repository: ICountryRepository
) : ViewModel() {

    var _state = MutableStateFlow(CountryListState())
    val state = _state.asStateFlow()

    fun onEvent(event: CountryDetailEvent) {
        when (event) {
            is CountryDetailEvent.AddToFavorite -> addToFavorite(country = event.country)
            is CountryDetailEvent.LoadCountry -> loadClickedCountry(event.cca3)
        }
    }

    private fun loadClickedCountry(cca3: String) {
        viewModelScope.launch {
            repository.getCountryByName(cca3)
                .collect { country ->
                    _state.value = _state.value.copy(selectedCountry = country)
                }
        }
    }

    private fun addToFavorite(country: Country?) {
        country?.let {
            val updateCountry = it.copy(isFavorite = !it.isFavorite)
            viewModelScope.launch {
                repository.updateCountry(updateCountry)
                _state.value = _state.value.copy(selectedCountry = updateCountry)
                Log.i("TAG", "Country update to favorite: ${!country.isFavorite}")
            }
        }
    }
}