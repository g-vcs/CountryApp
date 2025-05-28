package com.guilherme.countryapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "countries")
data class CountryEntity(
    @PrimaryKey
    val cca3: String,
    val name: String?,
    val capital: String?,
    val region: String?,
    val subregion: String?,
    val population: Long?,
    val flagPng: String?,
    val flagSvg: String?,
    val isFavorite: Boolean ?= false
)
