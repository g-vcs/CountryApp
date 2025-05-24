package com.guilherme.countryapp.data.remote

data class CountryDto(
    val cca3: String?,
    val name: CountryName?,
    val capital: List<String>?,
    val region: String?,
    val subregion: String?,
    val population: Long?,
    val flags: Flags?
)

data class CountryName(
    val common: String?,
    val official: String?
)

data class Flags(
    val png: String?,
    val svg: String?,
    val alt: String?,
)
