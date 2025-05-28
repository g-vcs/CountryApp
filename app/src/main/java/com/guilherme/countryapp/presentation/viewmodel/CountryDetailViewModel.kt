package com.guilherme.countryapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.guilherme.countryapp.domain.repository.ICountryRepository
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

    fun loadClickedCountry(cca3: String) {
        viewModelScope.launch {
            repository.getCountryByName(cca3)
                .collect { country ->
                    _state.value = _state.value.copy(selectedCountry = country)
                }
        }
    }
}