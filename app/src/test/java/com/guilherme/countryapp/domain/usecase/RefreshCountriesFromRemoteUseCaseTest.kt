package com.guilherme.countryapp.domain.usecase

import com.guilherme.countryapp.domain.repository.ICountryRepository
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test


class RefreshCountriesFromRemoteUseCaseTest {

    lateinit var sutRefreshCountriesFromRemoteUseCase: RefreshCountriesFromRemoteUseCase

    @MockK
    private val mockRepository: ICountryRepository = mockk(relaxed = true)

    @Before
    fun setup() {
        sutRefreshCountriesFromRemoteUseCase = RefreshCountriesFromRemoteUseCase(mockRepository)
    }

    @Test
    fun `test delete country usecase`() = runTest {
        // When
        sutRefreshCountriesFromRemoteUseCase.invoke()

        // Then
        coVerify(exactly = 1) { mockRepository.refreshCountriesFromRemote() }
    }

}