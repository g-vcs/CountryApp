package com.guilherme.countryapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface CountryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCountry(country: CountryEntity)

    @Update
    suspend fun updateCountry(country: CountryEntity)

    @Delete
    suspend fun deleteCountry(country: CountryEntity)

    @Query("SELECT * from countries ORDER BY cca3 ASC")
    fun getAllCountries():Flow<List<CountryEntity>>

    @Query("SELECT * from countries WHERE cca3 = :countryName")
    fun getCountry(countryName: String): Flow<CountryEntity>

    @Query("SELECT * from countries WHERE isFavorite = 1")
    fun getFavoriteCountries(): Flow<List<CountryEntity>>
}
