package com.guilherme.countryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.guilherme.countryapp.presentation.theme.CountryAppTheme
import com.guilherme.countryapp.presentation.ui.CountryListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CountryAppTheme {
                Scaffold { innerPadding ->
                    CountryListScreen(modifier = Modifier.padding(innerPadding))
                }
                /*Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {

                    Column {
                        Button(
                            onClick = {
                                lifecycleScope.launch(Dispatchers.IO) {
                                    country.insertCountry(
                                        CountryEntity(
                                            cca3 = "GUI",
                                            name = "Portugal",
                                            capital = "PORTO",
                                            region = "Europe",
                                            subregion = "Southern Europe",
                                            population = 10196709,
                                            flagPng = "https://flagcdn.com/w320/pt.png",
                                            flagSvg = "https://flagcdn.com/pt.svg"
                                        )
                                    )
                                }
                            }
                        ) {
                            Text("Insert in DB")
                        }

                        Button(
                            onClick = {
                                lifecycleScope.launch(Dispatchers.IO) {
                                    country.deleteCountry(
                                        CountryEntity(
                                            cca3 = "GUI",
                                            name = "Portugal",
                                            capital = "PORTO",
                                            region = "Europe",
                                            subregion = "Southern Europe",
                                            population = 10196709,
                                            flagPng = "https://flagcdn.com/w320/pt.png",
                                            flagSvg = "https://flagcdn.com/pt.svg"
                                        )
                                    )
                                }
                            }
                        ) {
                            Text("Delete in DB")
                        }

                        Button(
                            onClick = {
                                lifecycleScope.launch(Dispatchers.IO) {
                                    country.getCountry("GUI").collect { country ->
                                        Log.d("CountryDB", "Country in DB: $country")
                                    }
                                }
                            }
                        ) {
                            Text("Print DB")

                        }

                        Button(
                            onClick = {
                                lifecycleScope.launch(Dispatchers.IO) {
                                    Log.d("COUNTRY REMOTE", "${api.getAllCountries()}")
                                }
                            }
                        ) {
                            Text("Fetch remote data")
                        }
                    }


                }*/
            }
        }
    }
}