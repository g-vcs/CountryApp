# 🌍 CountryApp

CountryApp is a modern Android application built with Jetpack Compose that displays country data from a REST API and allows users to view, search, and favorite countries. The app follows clean architecture principles and leverages Kotlin coroutines, Hilt, Room, and Retrofit for a reactive and scalable codebase.

> ✅ The country data is fetched from the [REST Countries API v3](https://restcountries.com/).

## 🧱 Architecture

The project is structured according to **Clean Architecture**, ensuring clear separation of concerns:

- **Presentation Layer**: Jetpack Compose UI with `ViewModel`s managing `StateFlow` for reactive UI updates.
- **Domain Layer**: Contains models and interfaces (`ICountryRepository`) to abstract data operations.
- **Data Layer**: Contains both `RemoteRepository` and `LocalRepository`, with mapping logic (`toCountry`, `toCountryEntity`) to convert between layers.

The architecture promotes **testability**, **modularity**, and **scalability**.

## 📡 API

This app consumes the [REST Countries API v3](https://restcountries.com/), which provides rich data about countries, including names, capitals, regions, flags, and more.

- Base URL: `https://restcountries.com/v3.1/`
- Example endpoint: `https://restcountries.com/v3.1/all`

## 🧩 Libraries Used

### 🗃 Data & Network

- [Retrofit](https://square.github.io/retrofit/) – HTTP client for fetching remote data  
- [Gson Converter](https://github.com/square/retrofit/tree/master/retrofit-converters/gson) – JSON parsing  
- [Room](https://developer.android.com/jetpack/androidx/releases/room) – SQLite abstraction with Kotlin support  

### 🎨 UI

- [Jetpack Compose](https://developer.android.com/jetpack/compose) – Declarative UI framework  
- [Navigation Compose](https://developer.android.com/jetpack/compose/navigation) – For screen navigation  
- [Coil](https://github.com/coil-kt/coil) – Image loading with Compose integration  
- [Heart Switch](https://github.com/IgorMelo06/HeartSwitch) – Custom animated toggle switch  

### 🛡️ Dependency Injection

- [Hilt](https://dagger.dev/hilt/) – Dependency injection framework for Android  
- [Hilt Navigation Compose](https://developer.android.com/jetpack/compose/libraries#hilt-navigation) – Integration with Jetpack Compose  

### 🧪 Testing

- [JUnit](https://junit.org/junit5/) – Unit testing framework  
- [MockK](https://mockk.io/) – Mocking library for Kotlin  
- [kotlinx.coroutines.test](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-test/) – Testing suspend functions and flows  
- [Turbine](https://github.com/cashapp/turbine) – Flow testing utility  

## 🧪 Tests: Coverage & Strategy

The codebase includes robust **unit tests** for the data and presentation layers.

### ✅ What’s Covered

- **Repository tests**:
  - Validate correct interaction with `LocalRepository` and `RemoteRepository`
  - Ensure Flow emissions are returned as expected
  - Use `MockK` to verify coroutine calls (`insert`, `delete`, `update`)
  
- **ViewModel logic**:
  - Asserts `StateFlow` transitions on search, toggle favorites, and error states
  - Captures error propagation and loading state behavior
  - Uses `Turbine` for structured flow assertions
