package com.guilherme.countryapp.presentation.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.guilherme.countryapp.domain.model.Country
import com.guilherme.countryapp.domain.model.CountryName
import com.guilherme.countryapp.domain.model.Flags
import com.guilherme.countryapp.presentation.ui.UiEvent
import com.guilherme.countryapp.presentation.ui.UiState.NavigateToCountryDetails
import com.guilherme.countryapp.presentation.viewmodel.CountryListViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryListScreen(
    navigation: (Country) -> Unit = {},
    paddingValues: PaddingValues,
    modifier: Modifier
) {
    val viewModel = hiltViewModel<CountryListViewModel>()

    LaunchedEffect(viewModel.uiEvent) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.CountryClick -> navigation(event.country)
                is UiEvent.AddToFavorite -> viewModel.addToFavorite(event.country)
            }
        }
    }

    CountryList(
        modifier = Modifier.padding(paddingValues),
        onCountryClick = { country ->
            viewModel.onEvent(NavigateToCountryDetails(country))
        }
    )
}


@Composable
fun CountryList(
    modifier: Modifier,
    onCountryClick: (Country) -> Unit
) {
    val viewModel: CountryListViewModel = hiltViewModel()
    val countries = viewModel.countries.collectAsState(initial = emptyList())
    LazyColumn(
        modifier = modifier
    ) {
        items(countries.value) { country ->
            CountryItem(
                country = country,
                onCountryClick = { onCountryClick(country) }
            )
        }
    }
}


@Composable
fun CountryItem(
    country: Country,
    onCountryClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp, vertical = 2.dp),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, Color.LightGray),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        onClick = {
            onCountryClick()
        }
    ) {
        Text(
            modifier = Modifier.padding(12.dp),
            text = country.name?.common ?: "Unknown",
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Preview
@Composable
fun PreviewCountryItem() {
    CountryItem(
        Country(
            cca3 = "GUI",
            name = CountryName(common = "Portugal", official = ""),
            capital = listOf("porto"),
            region = "Europe",
            subregion = "Southern Europe",
            population = 10196709,
            flags = Flags(png = "", svg = "", alt = null)

        ), {}
    )
}