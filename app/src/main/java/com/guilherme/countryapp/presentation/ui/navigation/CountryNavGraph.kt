package com.guilherme.countryapp.presentation.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.guilherme.countryapp.presentation.ui.screen.CountryDetail
import com.guilherme.countryapp.presentation.ui.screen.CountryListScreen
import com.guilherme.countryapp.presentation.viewmodel.CountryDestination

@Composable
fun CountryNavHost(
    navController: NavHostController,
    padding: PaddingValues,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = CountryDestination.route,
        modifier = modifier
    ) {
        composable(
            route = CountryDestination.route,
        ) {
            CountryListScreen(
                navigation = { country -> navController.navigate("detail/${country.name?.common}") },
                paddingValues = padding,
                modifier = Modifier
            )
        }

        composable(
            route = "detail/{country}",
        ) { backstackEntry ->
            val country = backstackEntry.arguments?.getString("country") ?: "Unknown"
            CountryDetail(
                country = country,
                paddingValues = padding,
            )
        }
    }
}