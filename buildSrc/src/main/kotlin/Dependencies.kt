object Dependencies {
    private const val RETROFIT_VERSION = "2.9.0"
    private const val MOSHI_VERSION = "1.13.0"
    private const val KOIN_VERSION = "3.5.0"
    private const val ROOM_VERSION = "2.6.1"
    private const val COIL_VERSION = "2.6.0"
    private const val LIFECYCLE_VERSION = "2.6.2"
    private const val COMPOSE_VERSION = "1.5.4"
    private const val COMPOSE_NAVIGATION_VERSION = "2.7.6"
    private const val COMPOSE_ACTIVITY_VERSION = "1.8.2"
    private const val COMPOSE_MATERIAL_3_VERSION = "1.1.0"
    private const val CORE_VERSION = "1.12.0"
    private const val APP_COMPAT_VERSION = "1.6.1"
    private const val OKHTTP_VERSION = "4.12.0"

    object AndroidX {
        const val CORE_KTX = "androidx.core:core-ktx:$CORE_VERSION"
        const val APP_COMPAT = "androidx.appcompat:appcompat:$APP_COMPAT_VERSION"
        const val LIFECYCLE_RUNTIME_KTX = "androidx.lifecycle:lifecycle-runtime-ktx:$LIFECYCLE_VERSION"
        const val COMPOSE_ACTIVITY = "androidx.activity:activity-compose:$COMPOSE_ACTIVITY_VERSION"
        const val COMPOSE_NAVIGATION = "androidx.navigation:navigation-compose:$COMPOSE_NAVIGATION_VERSION"
        const val COMPOSE_TOLLING_PREVIEW = "androidx.compose.ui:ui-tooling-preview:$COMPOSE_VERSION"
        const val COMPOSE_UI_TOLLING = "androidx.compose.ui:ui-tooling:$COMPOSE_VERSION"
        const val COMPOSE_UI_TEST_MANIFEST = "androidx.compose.ui:ui-test-manifest:$COMPOSE_VERSION"
    }

    object UI {
        const val COMPOSE_UI = "androidx.compose.ui:ui:$COMPOSE_VERSION"
        const val COMPOSE_MATERIAL = "androidx.compose.material3:material3:$COMPOSE_MATERIAL_3_VERSION"
        const val COMPOSE_MATERIAL_DESIGN = "androidx.compose.material:material:$COMPOSE_VERSION"
        const val COIL_KTX = "io.coil-kt:coil-compose:$COIL_VERSION"
    }

    object DI {
        const val KOIN = "io.insert-koin:koin-androidx-compose:$KOIN_VERSION"
    }

    object NETWORK {
        const val OKHTTP = "com.squareup.okhttp3:logging-interceptor:$OKHTTP_VERSION"
    }

    object DataSource {
        const val RETROFIT = "com.squareup.retrofit2:retrofit:$RETROFIT_VERSION"
        const val RETROFIT_CONVERTER = "com.squareup.retrofit2:converter-moshi:$RETROFIT_VERSION"
        const val MOSHI_KOTLIN = "com.squareup.moshi:moshi-kotlin:$MOSHI_VERSION"
        const val ROOM = "androidx.room:room-ktx:$ROOM_VERSION"
        const val ROOM_COMPILER = "androidx.room:room-compiler:$ROOM_VERSION"
    }

    object UnitTest {
        const val KOIN = "io.insert-koin:koin-test:$KOIN_VERSION"
        const val JUNIT = "junit:junit:4.13.2"
        const val CORE_KTX = "androidx.test:core-ktx:1.4.0"
        const val CORE_TESTING = "androidx.arch.core:core-testing:2.1.0"
        const val MOCKK = "io.mockk:mockk:1.12.3"
        const val COROUTINES_TEST = "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.2"
    }
}
