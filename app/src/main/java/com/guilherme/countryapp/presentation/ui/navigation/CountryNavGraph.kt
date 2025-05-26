package com.guilherme.countryapp.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.guilherme.countryapp.presentation.ui.screen.CountryListScreen
import com.guilherme.countryapp.presentation.viewmodel.CountryDestination

@Composable
fun CountryNavHost(
    navController: NavHostController,
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
                navigation = { navController.navigate(CountryDestination.route) },
                modifier = Modifier
            )
        }
    }
}