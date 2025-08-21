package com.guilherme.countryapp.presentation.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.guilherme.countryapp.domain.model.Country
import com.guilherme.countryapp.presentation.ui.events.CountryDetailEvent
import com.guilherme.countryapp.presentation.ui.events.CountryDetailEvent.AddToFavorite
import com.guilherme.countryapp.presentation.viewmodel.CountryDetailViewModel


@Composable
fun CountryDetail(
    paddingValues: PaddingValues,
    cca3: String,
    viewmodel: CountryDetailViewModel = hiltViewModel()
) {
    val country by viewmodel.state.collectAsStateWithLifecycle()

    LaunchedEffect(cca3) {
        viewmodel.onEvent(CountryDetailEvent.LoadCountry(cca3))
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = country.selectedCountry?.flags?.png ?: country.selectedCountry?.flags?.svg,
                contentDescription = country.selectedCountry?.flags?.alt
            )

            Text(
                text = country.selectedCountry?.name?.common ?: "Unknown",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            CountryInfoRow(
                label = "Official Name",
                value = country.selectedCountry?.name?.official ?: "N/A"
            )
            CountryInfoRow(
                label = "Capital",
                value = country.selectedCountry?.capital?.joinToString() ?: "N/A"
            )
            CountryInfoRow(label = "Region", value = country.selectedCountry?.region ?: "N/A")
            CountryInfoRow(label = "Subregion", value = country.selectedCountry?.subregion ?: "N/A")
            CountryInfoRow(
                label = "Population",
                value = "%,d".format(country.selectedCountry?.population ?: 0)
            )
        }

        AddICountryToFavorite(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            country = country.selectedCountry,
            onClick = viewmodel::onEvent
        )
    }
}

@Composable
fun CountryInfoRow(label: String, value: String) {
    Column(modifier = Modifier.padding(vertical = 4.dp)) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = Color.Gray
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun AddICountryToFavorite(
    modifier: Modifier,
    country: Country?,
    onClick: (event: CountryDetailEvent) -> Unit
) {
    Button(
        modifier = modifier,
        onClick = { onClick(AddToFavorite(country)) }
    ) {
        country?.isFavorite?.let {
            if (it) {
                Icon(Icons.Default.Favorite, contentDescription = "Favorito")
            } else {
                Icon(Icons.Default.FavoriteBorder, contentDescription = "Não Favorito")
            }
        }
    }
}
