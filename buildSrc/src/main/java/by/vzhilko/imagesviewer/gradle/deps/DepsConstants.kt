package by.vzhilko.imagesviewer.gradle.deps

object DepsConstants {

    object Module {
        const val CORE: String = ":core"
        const val FEATURE_LIST: String = ":feature:list"
        const val FEATURE_DETAILS: String = ":feature:details"
    }

    object Kotlin {
        object Versions {
            const val COROUTINES: String = "1.6.4"
        }

        const val COROUTINES: String = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.COROUTINES}"
    }

    object Androidx {
        object Versions {
            const val CORE_KTX: String = "1.9.0"
            const val APPCOMPAT: String = "1.6.1"
            const val NAVIGATION: String = "2.5.3"
            const val LIFECYCLE: String = "2.5.1"
            const val PAGING: String = "3.1.1"
            const val ROOM: String = "2.4.3"
            const val COMPOSE: String = "1.3.2"
            const val COMPOSE_MATERIAL3: String = "1.0.0"
            const val COMPOSE_ACTIVITY: String = "1.6.1"
            const val COMPOSE_LIFECYCLE_VIEWMODEL: String = "2.5.1"
            const val COMPOSE_NAVIGATION: String = "2.5.3"
            const val COMPOSE_HILT_NAVIGATION: String = "1.0.0"
            const val COMPOSE_PAGING: String = "1.0.0-alpha17"
            const val COMPOSE_CONSTRAINT_LAYOUT: String = "1.0.1"
        }

        const val CORE_KTX: String = "androidx.core:core-ktx:${Versions.CORE_KTX}"
        const val APPCOMPAT: String = "androidx.appcompat:appcompat:${Versions.APPCOMPAT}"
        const val NAVIGATION_FRAGMENT_KTX: String = "androidx.navigation:navigation-fragment-ktx:${Versions.NAVIGATION}"
        const val NAVIGATION_UI_KTX: String = "androidx.navigation:navigation-ui-ktx:${Versions.NAVIGATION}"
        const val VIEW_MODEL_KTX: String = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.LIFECYCLE}"
        const val VIEW_MODEL_SAVED_STATE: String = "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.LIFECYCLE}"
        const val LIFECYCLE_COMPILER: String = "androidx.lifecycle:lifecycle-compiler:${Versions.LIFECYCLE}"
        const val PAGING: String = "androidx.paging:paging-runtime:${Versions.PAGING}"
        const val ROOM: String = "androidx.room:room-runtime:${Versions.ROOM}"
        const val ROOM_COMPILER: String = "androidx.room:room-compiler:${Versions.ROOM}"
        const val ROOM_KTX: String = "androidx.room:room-ktx:${Versions.ROOM}"
        const val ROOM_PAGING: String = "androidx.room:room-paging:${Versions.ROOM}"
        const val COMPOSE_RUNTIME: String = "androidx.compose.runtime:runtime:${Versions.COMPOSE}"
        const val COMPOSE_MATERIAL3: String = "androidx.compose.material3:material3:${Versions.COMPOSE_MATERIAL3}"
        const val COMPOSE_UI_TOOLING_PREVIEW: String = "androidx.compose.ui:ui-tooling-preview:${Versions.COMPOSE}"
        const val COMPOSE_UI_TOOLING: String = "androidx.compose.ui:ui-tooling:${Versions.COMPOSE}"
        const val COMPOSE_ACTIVITY: String = "androidx.activity:activity-compose:${Versions.COMPOSE_ACTIVITY}"
        const val COMPOSE_LIFECYCLE_VIEWMODEL: String = "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.COMPOSE_LIFECYCLE_VIEWMODEL}"
        const val COMPOSE_NAVIGATION: String = "androidx.navigation:navigation-compose:${Versions.COMPOSE_NAVIGATION}"
        const val COMPOSE_HILT_NAVIGATION: String = "androidx.hilt:hilt-navigation-compose:${Versions.COMPOSE_HILT_NAVIGATION}"
        const val COMPOSE_PAGING: String = "androidx.paging:paging-compose:${Versions.COMPOSE_PAGING}"
        const val COMPOSE_CONSTRAINT_LAYOUT: String = "androidx.constraintlayout:constraintlayout-compose:${Versions.COMPOSE_CONSTRAINT_LAYOUT}"
    }

    object Google {
        object Versions {
            const val MATERIAL: String = "1.7.0"
            const val DAGGER: String = "2.44"
            const val HILT: String = "2.44"
            const val GSON: String = "2.10"
        }

        const val MATERIAL: String = "com.google.android.material:material:${Versions.MATERIAL}"
        const val DAGGER: String = "com.google.dagger:dagger:${Versions.DAGGER}"
        const val DAGGER_COMPILER: String = "com.google.dagger:dagger-compiler:${Versions.DAGGER}"
        const val HILT: String = "com.google.dagger:hilt-android:${Versions.HILT}"
        const val HILT_COMPILER: String = "com.google.dagger:hilt-android-compiler:${Versions.HILT}"
        const val GSON: String = "com.google.code.gson:gson:${Versions.GSON}"
    }

    object Squareup {
        object Versions {
            const val RETROFIT: String = "2.9.0"
            const val HTTP_LOGGING_INTERCEPTOR: String = "4.10.0"
        }

        const val RETROFIT: String = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT}"
        const val RETROFIT_CONVERTER_GSON: String = "com.squareup.retrofit2:converter-gson:${Versions.RETROFIT}"
        const val HTTP_LOGGING_INTERCEPTOR: String = "com.squareup.okhttp3:logging-interceptor:${Versions.HTTP_LOGGING_INTERCEPTOR}"
    }

    object Coil {
        object Versions {
            const val COIL: String = "2.2.2"
            const val COIL_COMPOSE = "2.4.0"
        }

        const val COIL: String = "io.coil-kt:coil:${Versions.COIL}"
        const val COIL_COMPOSE: String = "io.coil-kt:coil-compose:${Versions.COIL_COMPOSE}"
    }

    object Facebook {
        object Versions {
            const val STETHO: String = "1.6.0"
        }

        const val STETHO: String = "com.facebook.stetho:stetho:${Versions.STETHO}"
        const val STETHO_OKHTTP: String = "com.facebook.stetho:stetho-okhttp3:${Versions.STETHO}"
    }

    object Test {
        object Versions {
            const val JUNIT: String = "4.13.2"
            const val JUNIT_EXT: String = "1.1.3"
            const val MOCKITO_CORE: String = "4.9.0"
            const val MOCKITO_KOTLIN = "4.1.0"
            const val MOCKITO_ANDROID = "4.9.0"
            const val COROUTINES_TEST: String = "1.6.4"
            const val ESPRESSO_CORE: String = "3.4.0"
            const val FRAGMENT_TESTING: String = "1.5.5"
            const val MONITOR: String = "1.6.1"
        }

        const val JUNIT: String = "junit:junit:${Versions.JUNIT}"
        const val JUNIT_EXT: String = "androidx.test.ext:junit:${Versions.JUNIT_EXT}"
        const val COROUTINES_TEST: String = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.COROUTINES_TEST}"
        const val ESPRESSO_CORE: String = "androidx.test.espresso:espresso-core:${Versions.ESPRESSO_CORE}"
        const val MOCKITO_CORE: String = "org.mockito:mockito-core:${Versions.MOCKITO_CORE}"
        const val MOCKITO_KOTLIN: String = "org.mockito.kotlin:mockito-kotlin:${Versions.MOCKITO_KOTLIN}"
        const val MOCKITO_ANDROID: String = "org.mockito:mockito-android:${Versions.MOCKITO_ANDROID}"
        const val FRAGMENT_TESTING: String = "androidx.fragment:fragment-testing:${Versions.FRAGMENT_TESTING}"
        const val MONITOR: String = "androidx.test:monitor:${Versions.MONITOR}"
    }

}