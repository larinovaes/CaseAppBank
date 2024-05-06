plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "br.com.itaucasebank"
    compileSdk = 34

    defaultConfig {
        applicationId = "br.com.itaucasebank"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(Dependencies.DI.KOIN)

    implementation(Dependencies.AndroidX.LIFECYCLE_RUNTIME_KTX)
    implementation(Dependencies.AndroidX.COMPOSE_ACTIVITY)
    implementation(Dependencies.AndroidX.COMPOSE_TOLLING_PREVIEW)
    implementation(Dependencies.AndroidX.COMPOSE_NAVIGATION)
    debugImplementation(Dependencies.AndroidX.COMPOSE_UI_TOLLING)
    debugImplementation(Dependencies.AndroidX.COMPOSE_UI_TEST_MANIFEST)
    implementation(Dependencies.AndroidX.CORE_KTX)
    implementation(Dependencies.AndroidX.APP_COMPAT)

    implementation(Dependencies.UI.COIL_KTX)
    implementation(Dependencies.UI.COMPOSE_UI)
    implementation(Dependencies.UI.COMPOSE_MATERIAL_DESIGN)
    implementation (Dependencies.UI.COMPOSE_MATERIAL)

    implementation(Dependencies.DataSource.RETROFIT)
    implementation(Dependencies.DataSource.RETROFIT_CONVERTER)
    implementation(Dependencies.DataSource.ROOM)
    kapt(Dependencies.DataSource.ROOM_COMPILER)
    implementation(Dependencies.DataSource.MOSHI_KOTLIN)

    implementation(Dependencies.NETWORK.OKHTTP)

    testImplementation(Dependencies.UnitTest.JUNIT)
    testImplementation(Dependencies.UnitTest.MOCKK)
    testImplementation(Dependencies.UnitTest.COROUTINES_TEST)
    testImplementation(Dependencies.UnitTest.CORE_TESTING)
}