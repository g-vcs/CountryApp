package com.guilherme.countryapp.domain.usecase

import com.guilherme.countryapp.domain.repository.ICountryRepository
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetCountryByNameUseCaseTest {

    lateinit var sutGetCountryByNameUseCase: GetCountryByNameUseCase

    @MockK
    private val mockRepository: ICountryRepository = mockk(relaxed = true)

    private val mockCountryName = "SomeMockName"

    @Before
    fun setup() {
        sutGetCountryByNameUseCase = GetCountryByNameUseCase(mockRepository)
    }

    @Test
    fun `test delete country usecase`() = runTest {
        // When
        sutGetCountryByNameUseCase.invoke(mockCountryName)

        // Then
        coVerify(exactly = 1) { mockRepository.getCountryByName(mockCountryName) }
    }

}
