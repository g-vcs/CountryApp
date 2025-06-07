package com.guilherme.countryapp.domain.usecase

import com.guilherme.countryapp.MockUtil.mockCountryPT
import com.guilherme.countryapp.domain.repository.ICountryRepository
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class InsertCountryUseCaseTest {

    lateinit var sutInsertCountryUseCase: InsertCountryUseCase

    @MockK
    private val mockRepository: ICountryRepository = mockk(relaxed = true)

    @Before
    fun setup() {
        sutInsertCountryUseCase = InsertCountryUseCase(mockRepository)
    }

    @Test
    fun `test insert country usecase`() = runTest {
        // When
        sutInsertCountryUseCase.invoke(mockCountryPT)

        // Then
        coVerify(exactly = 1) { mockRepository.insertCountry(mockCountryPT) }
    }

}
