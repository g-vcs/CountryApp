package com.guilherme.countryapp.domain.usecase

import com.guilherme.countryapp.domain.repository.ICountryRepository
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetFavoriteCountriesUseCaseTest {

    lateinit var sutGetFavoriteCountriesUseCase: GetFavoriteCountriesUseCase

    @MockK
    private val mockRepository: ICountryRepository = mockk(relaxed = true)

    @Before
    fun setup() {
        sutGetFavoriteCountriesUseCase = GetFavoriteCountriesUseCase(mockRepository)
    }

    @Test
    fun `test delete country usecase`() = runTest {
        // When
        sutGetFavoriteCountriesUseCase.invoke()

        // Then
        coVerify(exactly = 1) { mockRepository.getFavoriteCountries() }
    }

}
