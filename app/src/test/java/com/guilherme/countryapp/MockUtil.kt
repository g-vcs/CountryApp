package com.guilherme.countryapp

import com.guilherme.countryapp.domain.model.Country
import com.guilherme.countryapp.domain.model.CountryName
import com.guilherme.countryapp.domain.model.Flags

object MockUtil {
    val mockCountryPT = Country(
        "PT",
        CountryName("Portugal", "República Portuguesa"),
        listOf("Lisboa"),
        "Europe",
        "Southern",
        10000000,
        Flags("", "", null),
        isFavorite = false
    )

    private val mockCountryES = Country(
        "ES",
        CountryName("Espanha", "Reino de Espanha"),
        listOf("Madrid"),
        "Europe",
        "Southern",
        46000000,
        Flags("", "", null)
    )

    val listOfCountries = listOf(
        mockCountryPT,
        mockCountryES
    )

}