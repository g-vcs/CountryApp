package com.guilherme.countryapp.domain.usecase

import com.guilherme.countryapp.MockUtil.mockCountryPT
import com.guilherme.countryapp.domain.repository.ICountryRepository
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class DeleteCountryUseCaseTest {

    lateinit var sutDeleteUseCase: DeleteCountryUseCase

    @MockK
    private val mockRepository: ICountryRepository = mockk(relaxed = true)

    @Before
    fun setup() {
        sutDeleteUseCase = DeleteCountryUseCase(mockRepository)
    }

    @Test
    fun `test delete country usecase`() = runTest {
        // When
        sutDeleteUseCase.invoke(mockCountryPT)

        // Then
        coVerify(exactly = 1) { mockRepository.deleteCountry(mockCountryPT) }
    }

}
