package com.guilherme.countryapp.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import com.guilherme.countryapp.presentation.ui.navigation.Navigation.CountryDetailNav
import com.guilherme.countryapp.presentation.ui.navigation.Navigation.CountryListScreenNav
import com.guilherme.countryapp.presentation.ui.screen.CountryDetail
import com.guilherme.countryapp.presentation.ui.screen.CountryListScreen
import kotlinx.serialization.Serializable

sealed class Navigation : NavKey {
    @Serializable
    data object CountryListScreenNav : Navigation()

    @Serializable
    data class CountryDetailNav(val cca3: String) : Navigation()
}

@Composable
fun NavigationRoot(
    modifier: Modifier = Modifier,
) {
    val backStack = rememberNavBackStack(CountryListScreenNav)
    NavDisplay(
        backStack = backStack,
        entryDecorators = listOf(
            rememberSavedStateNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator(),
            rememberSceneSetupNavEntryDecorator()
        ),
        onBack = { backStack.removeLastOrNull() },
        entryProvider = { key ->
            when (key) {
                is CountryListScreenNav -> {
                    NavEntry(key = key) {
                        CountryListScreen(
                            navigation = { country ->
                                country.cca3?.let {
                                    backStack.add(CountryDetailNav(it))
                                }
                            }
                        )
                    }
                }
                is CountryDetailNav -> {
                    NavEntry(key = key) {
                        CountryDetail(cca3 = key.cca3)
                    }
                }
                else -> throw RuntimeException("Invalid NavKey")
            }
        }
    )
}