plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.jetbrains.kotlin.serialization)

    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
    id("jacoco")
}

android {
    namespace = "com.guilherme.countryapp"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.guilherme.countryapp"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            enableUnitTestCoverage = true
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
    testOptions {
        unitTests.isReturnDefaultValues = true
    }
}

tasks.register<JacocoReport>("jacocoTestReport") {
    dependsOn("testDebugUnitTest")

    reports {
        xml.required.set(true)
        html.required.set(true)
    }

    val fileFilter = listOf(
        "**/R.class", "**/R$*.class", "**/BuildConfig.*",
        "**/Manifest*.*", "**/*Test*.*", "**/Hilt_*.class",
        "**/*\$*\$InjectAdapter.class", "android/**/*.*",
        "**/CountryUseCases.class"
    )

    val includedPackages = listOf(
        "com/guilherme/countryapp/presentation/viewmodel/**",
        "com/guilherme/countryapp/domain/usecase/**",
        "com/guilherme/countryapp/data/repository/**",
        "com/guilherme/countryapp/domain/model/**"
    )

    classDirectories.setFrom(
        files(
            fileTree(layout.buildDirectory.dir("tmp/kotlin-classes/debug").get().asFile) {
                include(includedPackages)
                exclude(fileFilter)
            },
            fileTree(layout.buildDirectory.dir("/intermediates/javac/debug/classes").get().asFile) {
                include(includedPackages)
                exclude(fileFilter)
            }
        )
    )

    sourceDirectories.setFrom(files("src/main/java", "src/main/kotlin"))

    executionData.setFrom(

        fileTree(layout.buildDirectory.get().asFile) {
            include(
                "jacoco/testDebugUnitTest.exec",
                "outputs/unit_test_code_coverage/debugUnitTest/testDebugUnitTest.exec"
            )
        }
    )

    doLast {
        println("✔ JaCoCo report generated: file://${layout.buildDirectory.get().asFile}/reports/jacoco/jacocoTestReport/html/index.html")
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    // Hilt Dagger
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    implementation(libs.androidx.hilt.navigation.compose)

    // Room
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler) // For Kotlin annotation processing
    implementation(libs.androidx.room.ktx) // Optional extensions for Kotlin

    // Compose
    implementation(libs.androidx.lifecycle.runtime.ktx.v287)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.navigation.compose)

    // Heart Switch
    implementation(libs.heart.switch)

    // Coil
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)

    // JUnit
    testImplementation(libs.junit)

    // MockK
    testImplementation(libs.mockk)

    // Test coroutines
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(kotlin("test"))

    // Turbine
    testImplementation(libs.turbine)

    // Navigation3
    implementation(libs.nav3.runtime)
    implementation(libs.nav3.ui)
    implementation(libs.androidx.lifecycle.viewmodel.nav3)
    implementation(libs.kotlinx.serialization.core)
    implementation(libs.kotlinx.serialization.json)

    // Material3
    implementation(libs.androidx.compose.material3.windowsizeclass)
    implementation(libs.androidx.material3.adaptive)
}