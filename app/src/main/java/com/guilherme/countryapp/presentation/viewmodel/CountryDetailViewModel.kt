package com.guilherme.countryapp.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.guilherme.countryapp.domain.repository.ICountryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class CountryDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: ICountryRepository
) : ViewModel() {

    val countryName: String = savedStateHandle["countryName"] ?: "Unknown"
    val countryFlow = repository.getCountries().map { list ->
        list.firstOrNull { it.name?.common == countryName }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        null
    )

}