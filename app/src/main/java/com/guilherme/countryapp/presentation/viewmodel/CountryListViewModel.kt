package com.guilherme.countryapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.guilherme.countryapp.domain.repository.ICountryRepository
import com.guilherme.countryapp.presentation.ui.navigation.NavigationDestination
import com.guilherme.countryapp.presentation.ui.states.CountryListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

object CountryDestination : NavigationDestination {
    override val route = "home"
    override val TitleRes = "CountryApp"
}

@HiltViewModel
class CountryListViewModel @Inject constructor(
    private val repository: ICountryRepository
) : ViewModel() {

    private val allCountries = repository.getCountries()
    private val favoritesCountries = repository.getFavoriteCountries()

    private val _state = MutableStateFlow(CountryListState())
    val state = _state.asStateFlow()

    val searchedCountries =
        combine(state, allCountries, favoritesCountries) { uiState, countries, favorites ->
            val query = uiState.searchQuery.trim()

            if (uiState.isShowingFavorites) {
                favorites.filter {
                    it.name?.common?.contains(query, ignoreCase = true) == true
                }
            } else {
                countries.filter {
                    it.name?.common?.contains(query, ignoreCase = true) == true
                }
            }

        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    init {
        fetchCountries()
    }

    fun fetchCountries() {
        viewModelScope.launch {
            repository.refreshCountriesFromRemote()
        }
    }


    fun onSearchQueryChanged(query: String) {
        _state.value = _state.value.copy(searchQuery = query)
    }

    fun showFavorite(isShown: Boolean) {
        _state.value = _state.value.copy(isShowingFavorites = isShown)

    }
}
