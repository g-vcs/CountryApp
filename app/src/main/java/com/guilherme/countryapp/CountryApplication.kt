package com.guilherme.countryapp

import android.app.Application
import com.guilherme.countryapp.data.local.AppDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CountryApplication : Application() {
    companion object {
        lateinit var countryDB: AppDatabase
    }
}