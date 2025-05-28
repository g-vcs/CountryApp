package com.guilherme.countryapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.guilherme.countryapp.domain.model.Country
import com.guilherme.countryapp.domain.repository.ICountryRepository
import com.guilherme.countryapp.presentation.ui.navigation.NavigationDestination
import com.guilherme.countryapp.presentation.ui.states.CountryListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

object CountryDestination : NavigationDestination {
    override val route = "home"
    override val TitleRes = "CountryApp"
}

@HiltViewModel
class CountryListViewModel @Inject constructor(
    private val repository: ICountryRepository
) : ViewModel() {

    private val allCountries = repository.getCountries()
    private val favoritesCountries = repository.getFavoriteCountries()

    val _state = MutableStateFlow(CountryListState())
    val state = _state.asStateFlow()

    init {
        fetchCountries()
    }

    fun fetchCountries() {
        viewModelScope.launch {
            repository.refreshCountriesFromRemote()
        }
    }

    fun observeCountries() {
        viewModelScope.launch {
            allCountries.collect { countries ->
                _state.value = _state.value.copy(countries = countries)
            }
        }
    }

    fun onCountryClicked(country: Country) {
        viewModelScope.launch {
            _state.value = _state.value.copy(selectedCountry = country)
        }
    }

    fun addToFavorite(country: Country) {
        viewModelScope.launch {
            repository.insertCountry(country)
        }
    }

    fun getFavoriteCountries() {
        viewModelScope.launch {
            repository.getFavoriteCountries()
        }
    }

}
