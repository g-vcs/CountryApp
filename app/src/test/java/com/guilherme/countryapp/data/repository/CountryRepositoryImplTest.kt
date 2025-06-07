package com.guilherme.countryapp.data.repository

import android.util.Log
import com.guilherme.countryapp.MockUtil.listOfCountries
import com.guilherme.countryapp.MockUtil.mockCountryPT
import com.guilherme.countryapp.data.local.LocalRepository
import com.guilherme.countryapp.data.remote.RemoteRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.mockkStatic
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class CountryRepositoryImplTest {

    @MockK
    val localRepo: LocalRepository = mockk(relaxed = true)

    @MockK
    val remoteRepo: RemoteRepository = mockk()

    lateinit var sutRepoImpl: CountryRepositoryImpl

    companion object {
        private val mockCountry = "mockCountry"
    }

    @Before
    fun setup() {
        mockkStatic(Log::class)
        every { Log.d(any(), any()) } returns 0

        sutRepoImpl = CountryRepositoryImpl(localRepo, remoteRepo)

    }

    @Test
    fun `getCountries should emit countries from local repository`() = runTest {
        // Given
        coEvery { localRepo.getCountries() } returns flowOf(listOfCountries)

        // When
        val result = sutRepoImpl.getCountries().first()

        //Then
        assertEquals(listOfCountries.first(), result.first())
        assertEquals(listOfCountries.size, result.size)
    }

    @Test
    fun `getCountryByName should emit country from local repository`() = runTest {
        // Given
        coEvery { localRepo.getCountryByName(mockCountry) } returns flowOf(mockCountryPT)

        // When
        val result = sutRepoImpl.getCountryByName(mockCountry).first()

        //Then
        assertEquals(listOfCountries.first(), result)
    }

    @Test
    fun `getFavoriteCountries should emit countries from local repository`() = runTest {
        // Given
        coEvery { localRepo.getFavoriteCountry() } returns flowOf(listOfCountries)

        // When
        val result = sutRepoImpl.getFavoriteCountries().first()

        //Then
        assertEquals(listOfCountries.first(), result.first())
    }

    @Test
    fun `insert Country in local repository`() = runTest {
        // When
        sutRepoImpl.insertCountry(mockCountryPT)

        //Then
        coVerify(exactly = 1) { localRepo.insertCountry(mockCountryPT) }
    }

    @Test
    fun `deleteCountry Country in local repository`() = runTest {
        // When
        sutRepoImpl.deleteCountry(mockCountryPT)

        //Then
        coVerify(exactly = 1) { localRepo.deleteCountry(mockCountryPT) }
    }

    @Test
    fun `updateCountry Country in local repository`() = runBlocking {
        // When
        sutRepoImpl.updateCountry(mockCountryPT)

        //Then
        coVerify(exactly = 1) { localRepo.updateCountry(mockCountryPT) }
    }

    @Test
    fun `refreshCountriesFromRemote should return an list of Country`() = runBlocking {
        // Given
        coEvery { remoteRepo.refreshCountriesFromRemote() } returns listOfCountries

        // When
        sutRepoImpl.refreshCountriesFromRemote()

        //Then
        listOfCountries.forEach {
            coVerify { localRepo.insertCountry(it) }
        }
        coVerify(exactly = 1) {
            remoteRepo.refreshCountriesFromRemote()
        }
    }

}