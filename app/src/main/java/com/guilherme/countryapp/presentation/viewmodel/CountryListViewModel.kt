package com.guilherme.countryapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.guilherme.countryapp.domain.model.Country
import com.guilherme.countryapp.domain.repository.ICountryRepository
import com.guilherme.countryapp.presentation.ui.events.UiCountryListEvent
import com.guilherme.countryapp.presentation.ui.events.UiCountryListEvent.CountryClick
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
    val _uiCountryListEvent = MutableSharedFlow<UiCountryListEvent>()
    val uiCountryListEvent = _uiCountryListEvent.asSharedFlow()

    init {
        fetchCountries()
    }

    fun fetchCountries() {
        viewModelScope.launch {
            repository.refreshCountriesFromRemote()
        }
    }

    fun onCountryClicked(country: Country) {
        viewModelScope.launch {
            _uiCountryListEvent.emit((CountryClick(country)))

        }
    }

    fun addToFavorite(country: Country) {
        viewModelScope.launch {
            repository.insertCountry(country)
        }
    }

}
