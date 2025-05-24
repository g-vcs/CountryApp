package com.guilherme.countryapp.presentation.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.guilherme.countryapp.presentation.viewmodel.CountryListViewModel
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun CountryList (
    viewModel: CountryListViewModel = hiltViewModel()
) {
}