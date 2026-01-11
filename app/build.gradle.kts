import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.navigation.safeargs)
    alias(libs.plugins.google.services)
    alias(libs.plugins.hilt.plugin)
    id("kotlin-parcelize")
}

android {
    namespace = "com.canerture.booksapp"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.canerture.booksapp"
        minSdk = 23
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
        }
    }

    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlin {
        compilerOptions.jvmTarget.set(JvmTarget.JVM_17)
    }
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.legacy.support.v4)

    // Navigation
    implementation(libs.navigation.fragment.ktx)
    implementation(libs.navigation.ui.ktx)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    // ViewModel
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.activity.ktx)

    // LiveData
    implementation(libs.lifecycle.livedata.ktx)

    // Picasso
    implementation(libs.picasso)

    // RecyclerView for Search
    implementation(libs.recyclerview)

    // Lottie
    implementation(libs.lottie)

    // Animated Svg for Splash
    implementation(libs.animated.svg.view)

    // Room
    implementation(libs.room.runtime)
    ksp(libs.room.compiler)

    // Firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth.ktx)
    implementation(libs.firebase.firestore.ktx)

    // Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
}