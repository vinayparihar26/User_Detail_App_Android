plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.jetbrains.kotlin.parcelize)
    alias(libs.plugins.jetbrains.kotlin.kapt) apply false
    alias(libs.plugins.com.google.devtools.ksp)
}

android {
    namespace = "com.nextday.userdetailapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.nextday.userdetailapp"
        minSdk = 24
        targetSdk = 35
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
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    viewBinding {
        enable = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Retrofit core dependency
    implementation (libs.retrofit)  // Retrofit 2.x

    // Gson converter for Retrofit
    implementation(libs.converter.gson.v290)

    // Retrofit adapter for RxJava3
    implementation(libs.adapter.rxjava3)

    // RxJava3 (version 3.x.x)
    implementation(libs.rxjava3.rxjava)

    // RxAndroid for Android-specific threads
    implementation(libs.rxjava3.rxandroid)

    // OkHttp and Logging Interceptor for Retrofit requests
    implementation(libs.okhttp.v490)
    implementation(libs.okhttp3.logging.interceptor.v490)

    implementation (libs.glide)

    //room dependency
    implementation(libs.androidx.room.runtime)
    ksp(libs.room.compiler)
    implementation(libs.androidx.room.ktx)
    testImplementation(libs.androidx.room.testing)

    // Coroutines
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    // Core & ViewModel & Livedata
    implementation(libs.androidx.core.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    implementation(libs.material3)
    //noinspection GradleDependency
    implementation (libs.androidx.foundation)
    implementation (libs.androidx.material.icons.extended)
}