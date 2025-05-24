package com.guilherme.countryapp.data.mapper

import com.guilherme.countryapp.data.remote.CountryDto
import com.guilherme.countryapp.domain.model.Country
import com.guilherme.countryapp.domain.model.CountryName
import com.guilherme.countryapp.domain.model.Flags
import com.guilherme.countryapp.data.remote.CountryName as CountryNameDto
import com.guilherme.countryapp.data.remote.Flags as FlagsDto


fun CountryDto.toCountry(): Country = Country(
    cca3 = cca3,
    name = name?.let { CountryName(common = it.common, official = it.official) },
    capital = capital,
    region = region,
    subregion = subregion,
    population = population,
    flags = Flags(png = flags?.png, svg = flags?.svg, alt = flags?.alt),
)

fun Country.toCountryDto(): CountryDto = CountryDto(
    cca3 = cca3,
    name = name?.let { CountryNameDto(common = it.common, official = it.official) },
    capital = capital,
    region = region,
    subregion = subregion,
    population = population,
    flags = FlagsDto(png = flags?.png, svg = flags?.svg, alt = flags?.alt),
)