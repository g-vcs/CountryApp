package com.guilherme.countryapp.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.guilherme.countryapp.domain.model.Country
import com.guilherme.countryapp.domain.repository.ICountryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryListViewModel @Inject constructor(
    private val repository: ICountryRepository
) : ViewModel() {

    val countries = repository.getCountries()

    init {
        fetchCountries()
    }

    fun fetchCountries() {
        viewModelScope.launch {
            repository.refreshCountriesFromRemote()
        }
    }
}