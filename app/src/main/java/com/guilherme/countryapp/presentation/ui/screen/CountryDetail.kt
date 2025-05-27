package com.guilherme.countryapp.presentation.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.guilherme.countryapp.presentation.viewmodel.CountryDetailViewModel

@Composable
fun CountryDetail(
    paddingValues: PaddingValues
) {
    val viewmodel: CountryDetailViewModel = hiltViewModel()
    val country by viewmodel.countryFlow.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Country Name
        Text(
            text = country?.name?.common ?: "Unknown",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Details
        CountryInfoRow(label = "Official Name", value = country?.name?.official ?: "N/A")
        CountryInfoRow(label = "Capital", value = country?.capital?.joinToString() ?: "N/A")
        CountryInfoRow(label = "Region", value = country?.region ?: "N/A")
        CountryInfoRow(label = "Subregion", value = country?.subregion ?: "N/A")
        CountryInfoRow(label = "Population", value = "%,d".format(country?.population ?: 0))
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