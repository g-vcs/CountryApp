package com.guilherme.countryapp.presentation.viewmodel

import com.guilherme.countryapp.MockUtil.mockCountryPT
import com.guilherme.countryapp.domain.repository.ICountryRepository
import com.guilherme.countryapp.presentation.ui.events.CountryDetailEvent
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class CountryDetailViewModelTest {

    lateinit var sutCountryDetail: CountryDetailViewModel

    private val mockRepository: ICountryRepository = mockk(relaxed = true)
    private val mockCca3 = "PT"

    @Before
    fun setup() {
        sutCountryDetail = CountryDetailViewModel(mockRepository)

        coEvery { mockRepository.getCountryByName(any()) } returns flowOf(mockCountryPT)
        coEvery { mockRepository.updateCountry(mockCountryPT) } just Runs
    }

    @Test
    fun `when country is clicked should show the country details`() = runTest {
        // When
        sutCountryDetail.loadClickedCountry(mockCca3)

        // Then
        coVerify(exactly = 1) { mockRepository.getCountryByName(mockCca3) }
        assertEquals(sutCountryDetail.state.value.selectedCountry?.cca3, mockCca3)
    }

    @Test
    fun `onEvent AddToFavorite should toggle isFavorite and update state`() = runTest {
        // Given
        val toggleFavorite = mockCountryPT.copy(isFavorite = !mockCountryPT.isFavorite)

        // When
        sutCountryDetail.onEvent(CountryDetailEvent.AddToFavorite(mockCountryPT))
        advanceUntilIdle()

        // Then
        coVerify { mockRepository.updateCountry(toggleFavorite) }

        val currentState = sutCountryDetail.state.value
        assertEquals(toggleFavorite, currentState.selectedCountry)

    }

}