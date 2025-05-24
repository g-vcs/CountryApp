package com.guilherme.countryapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.guilherme.countryapp.data.local.CountryDao
import com.guilherme.countryapp.data.local.CountryEntity
import com.guilherme.countryapp.data.remote.CountryService
import com.guilherme.countryapp.presentation.theme.CountryAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var country: CountryDao

    @Inject
    lateinit var api: CountryService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CountryAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "",
                        modifier = Modifier.padding(innerPadding)
                    )
                    Box(
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


                    }
                }
            }
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CountryAppTheme {
        Greeting("Android")
    }
}