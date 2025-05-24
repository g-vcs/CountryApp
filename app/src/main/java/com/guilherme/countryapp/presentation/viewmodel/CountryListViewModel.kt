package com.guilherme.countryapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.guilherme.countryapp.domain.repository.ICountryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CountryListViewModel @Inject constructor(
    private val repository: ICountryRepository
) : ViewModel() {
}