package com.guilherme.countryapp.presentation.viewmodel

import android.util.Log
import app.cash.turbine.test
import com.guilherme.countryapp.MockUtil
import com.guilherme.countryapp.domain.repository.ICountryRepository
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertTrue

class CountryListViewModelTest {

    private lateinit var sutViewModel: CountryListViewModel
    private lateinit var repository: ICountryRepository

    private val fakeCountries = MockUtil.listOfCountries

    @Before
    fun setup() {
        mockkStatic(Log::class)
        every { Log.d(any(), any()) } returns 0

        repository = mockk(relaxed = true)

        coEvery { repository.getCountries() } returns flowOf(fakeCountries)
        coEvery { repository.getFavoriteCountries() } returns flowOf(listOf(fakeCountries[0]))
        coEvery { repository.refreshCountriesFromRemote() } returns Unit

        sutViewModel = CountryListViewModel(repository)
        sutViewModel.onSearchQueryChanged("")

    }

    @Test
    fun `assert initial state should have countries loaded`() = runTest {
        // When
        sutViewModel.searchedCountries.test {
            awaitItem()

            val filteredList = awaitItem()

            // Then
            assertEquals(2, filteredList.size)
            assertEquals("PT", filteredList.first().cca3)
            assertEquals("ES", filteredList[1].cca3)

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `searchedCountries emits result with Portugal`() = runTest {
        // Given
        sutViewModel.onSearchQueryChanged("Portugal")

        // When
        sutViewModel.searchedCountries.test {
            awaitItem()

            val filteredList = awaitItem()

            // Then
            assertEquals(1, filteredList.size)
            assertEquals("PT", filteredList.first().cca3)

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `showFavorite updates the state to true`() = runTest {
        // Given
        sutViewModel.showFavorite(true)

        // When
        sutViewModel.state.test {
            val state = awaitItem()

            // Then
            assertTrue(state.isShowingFavorites)
            cancelAndIgnoreRemainingEvents()
        }
    }
}