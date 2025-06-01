package com.guilherme.countryapp.data.mapper

import com.guilherme.countryapp.data.local.CountryEntity
import com.guilherme.countryapp.domain.model.Country
import com.guilherme.countryapp.domain.model.CountryName
import com.guilherme.countryapp.domain.model.Flags

fun CountryEntity.toCountry(): Country = Country(
    cca3 = cca3,
    name = CountryName(common = name, official = null),
    capital = capital?.split(",")?.map { it.trim() },
    region = region,
    subregion = subregion,
    population = population,
    flags = Flags(png = flagPng, svg = flagSvg, alt = null),
    isFavorite = isFavorite
)

fun Country.toCountryEntity(): CountryEntity = CountryEntity(
    cca3 = cca3 ?: "N/A",
    name = name?.common ?: "Unknown",
    capital = capital?.joinToString(","),
    region = region,
    subregion = subregion,
    population = population,
    flagPng = flags?.png,
    flagSvg = flags?.svg,
    isFavorite = isFavorite
)
