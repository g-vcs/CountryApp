package com.guilherme.countryapp.data.remote

import android.util.Log
import com.guilherme.countryapp.data.mapper.toCountry
import com.guilherme.countryapp.domain.model.Country
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RemoteRepository @Inject constructor(private val api: CountryService) {
    val TAG = "RemoteRepository"

    suspend fun refreshCountriesFromRemote(): List<Country> {
        return try {
            val result = api.getAllCountries("cca3,name,capital,region,subregion,population,flags")
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
