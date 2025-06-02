package com.guilherme.countryapp.presentation.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.guilherme.countryapp.domain.model.Country
import com.guilherme.countryapp.domain.model.CountryName
import com.guilherme.countryapp.domain.model.Flags
import com.guilherme.countryapp.presentation.viewmodel.CountryListViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryListScreen(
    navigation: (Country) -> Unit = {},
    paddingValues: PaddingValues,
    modifier: Modifier
) {
    val viewModel = hiltViewModel<CountryListViewModel>()
    val uiState by viewModel.state.collectAsState()
    val countryList by viewModel.searchedCountries.collectAsState()

    CountryList(
        modifier = Modifier.padding(paddingValues),
        viewModel = viewModel,
        searchQuery = uiState.searchQuery,
        searchedCountries = countryList,
        onCountryClick = navigation
    )
}

@Composable
fun CountryList(
    modifier: Modifier,
    viewModel: CountryListViewModel,
    searchQuery: String,
    searchedCountries: List<Country>,
    onCountryClick: (Country) -> Unit
) {

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        SearchBar(searchQuery, viewModel)

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            items(searchedCountries) { country ->
                CountryItem(
                    country = country,
                    onCountryClick = { onCountryClick(country) }
                )
            }
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

@Composable
fun SearchBar(
    searchQuery: String,
    viewModel: CountryListViewModel
) {
    OutlinedTextField(
        value = searchQuery,
        onValueChange = viewModel::onSearchQueryChanged,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        placeholder = { Text("Search country", color = Color.Gray) },
        singleLine = true,
        keyboardActions = KeyboardActions(onSearch = {}),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Text
        ),
    )
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