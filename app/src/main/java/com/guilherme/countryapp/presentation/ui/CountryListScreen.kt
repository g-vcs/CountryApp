package com.guilherme.countryapp.presentation.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.guilherme.countryapp.domain.model.Country
import com.guilherme.countryapp.domain.model.CountryName
import com.guilherme.countryapp.domain.model.Flags
import com.guilherme.countryapp.presentation.viewmodel.CountryListViewModel


@Composable
fun CountryListScreen(
    modifier: Modifier
) {
    val viewModel: CountryListViewModel = hiltViewModel()
    val countries = viewModel.countries.collectAsState(initial = emptyList())
    LazyColumn (
        modifier = modifier
    ) {
        items(countries.value) { country ->
            CountryItem(country)
        }
    }
}


@Composable
fun CountryItem(country: Country) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp, vertical = 2.dp),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, Color.LightGray),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
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

        )
    )
}