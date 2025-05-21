package com.guilherme.countryapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update

@Dao
interface CountryDao {
    @Insert
    suspend fun insertCountry(country: CountryEntity)

    @Update
    suspend fun updateCountry(country: CountryEntity)

    @Delete
    suspend fun deleteCountry(country: CountryEntity)
}