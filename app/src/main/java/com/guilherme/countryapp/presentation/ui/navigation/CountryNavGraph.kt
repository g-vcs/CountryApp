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
                navigation = { country -> navController.navigate("detail/${country.cca3}") },
                paddingValues = padding,
                modifier = Modifier
            )
        }

        composable(
            route = "detail/{cca3}",
        ) { backstackEntry ->
            val cca3 = backstackEntry.arguments?.getString("cca3") ?: "UNKNOWN"
            CountryDetail(
                paddingValues = padding,
                cca3 = cca3
            )
        }
    }
}