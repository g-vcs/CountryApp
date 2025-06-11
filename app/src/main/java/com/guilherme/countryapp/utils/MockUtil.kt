package com.guilherme.countryapp.utils

import com.guilherme.countryapp.domain.model.Country
import com.guilherme.countryapp.domain.model.CountryName
import com.guilherme.countryapp.domain.model.Flags

object MockUtil {
    val mockCountryBR = Country(
        "BR",
        CountryName("Brazil", "República Brasileira"),
        listOf("Brasilia"),
        "America",
        "Southern",
        200000000,
        Flags("", "", null),
        isFavorite = false
    )

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

    val mockCountryES = Country(
        "ES",
        CountryName("Espanha", "Reino de Espanha"),
        listOf("Madrid"),
        "Europe",
        "Southern",
        46000000,
        Flags("", "", null)
    )

    val listOfCountries = listOf(
        mockCountryBR,
        mockCountryPT,
        mockCountryES
    )

}