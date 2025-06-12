package com.guilherme.countryapp.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Country(
    val cca3: String?,
    val name: CountryName?,
    val capital: List<String>?,
    val region: String?,
    val subregion: String?,
    val population: Long?,
    val flags: Flags?,
    val isFavorite: Boolean = false
)

@Serializable
data class CountryName(
    val common: String?,
    val official: String?
)

@Serializable
data class Flags(
    val png: String?,
    val svg: String?,
    val alt: String?,
)
