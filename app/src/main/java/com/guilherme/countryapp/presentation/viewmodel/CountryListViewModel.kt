package com.guilherme.countryapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.guilherme.countryapp.domain.model.Country
import com.guilherme.countryapp.domain.repository.ICountryRepository
import com.guilherme.countryapp.presentation.ui.UiEvent
import com.guilherme.countryapp.presentation.ui.UiEvent.CountryClick
import com.guilherme.countryapp.presentation.ui.UiState
import com.guilherme.countryapp.presentation.ui.UiState.NavigateToCountryDetails
import com.guilherme.countryapp.presentation.ui.navigation.NavigationDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
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
    val countries = repository.getCountries()
    val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    init {
        fetchCountries()
    }

    fun onEvent(event: UiState) {
        viewModelScope.launch {
            when (event) {
                is NavigateToCountryDetails -> navigateToCountryDetails(event.country)
            }
        }
    }

    fun fetchCountries() {
        viewModelScope.launch {
            repository.refreshCountriesFromRemote()
        }
    }

    fun navigateToCountryDetails(country: Country) {
        viewModelScope.launch {
            _uiEvent.emit(CountryClick(country))
        }
    }


    fun addToFavorite(country: Country) {
        viewModelScope.launch {
            repository.insertCountry(country)
        }
    }

}