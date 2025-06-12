package com.guilherme.countryapp.domain.usecase

import com.guilherme.countryapp.MockUtil.mockCountryPT
import com.guilherme.countryapp.domain.repository.ICountryRepository
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class UpdateCountryUseCaseTest {

    lateinit var sutUpdateCountryUseCase: UpdateCountryUseCase

    @MockK
    private val mockRepository: ICountryRepository = mockk(relaxed = true)

    @Before
    fun setup() {
        sutUpdateCountryUseCase = UpdateCountryUseCase(mockRepository)
    }

    @Test
    fun `test update country usecase`() = runTest {
        // When
        sutUpdateCountryUseCase.invoke(mockCountryPT)

        // Then
        coVerify(exactly = 1) { mockRepository.updateCountry(mockCountryPT) }
    }

}
