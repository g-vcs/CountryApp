package com.guilherme.countryapp.domain.usecase

import com.guilherme.countryapp.domain.repository.ICountryRepository
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetAllCountriesUseCaseTest {

    lateinit var sutGetAllCountriesUseCase: GetAllCountriesUseCase

    @MockK
    private val mockRepository: ICountryRepository = mockk(relaxed = true)

    @Before
    fun setup() {
        sutGetAllCountriesUseCase = GetAllCountriesUseCase(mockRepository)
    }

    @Test
    fun `test delete country usecase`() = runTest {
        // When
        sutGetAllCountriesUseCase.invoke()

        // Then
        coVerify(exactly = 1) { mockRepository.getCountries() }
    }

}
