package com.guilherme.countryapp.data.remote

import android.util.Log
import com.guilherme.countryapp.data.mapper.toCountry
import com.guilherme.countryapp.domain.model.Country
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RemoteRepository @Inject constructor(private val api: CountryService) {
    val TAG = "RemoteRepository"

    suspend fun refreshCountriesFromRemote(): List<Country>  {
        return try {
            val result = api.getAllCountries()
            result.map { it.toCountry() }
        } catch (e: HttpException) {
            Log.d(TAG, "Exception: $e")
            emptyList()
        } catch (e: IOException) {
            Log.d(TAG, "Exception: $e")
            emptyList()
        } catch (e: Exception) {
            Log.d(TAG, "Exception: $e")
            emptyList()
        }
    }
}
