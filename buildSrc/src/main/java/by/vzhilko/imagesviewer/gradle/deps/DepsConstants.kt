package by.vzhilko.imagesviewer.gradle.deps

object DepsConstants {

    object Module {
        const val CORE: String = ":core"
        const val FEATURE_LIST: String = ":feature:list"
        const val FEATURE_DETAILS: String = ":feature:details"
    }

    object Kotlin {
        object Versions {
            const val COROUTINES: String = "1.3.9"
        }

        const val COROUTINES: String = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.COROUTINES}"
    }

    object Androidx {
        object Versions {
            const val CORE_KTX: String = "1.9.0"
            const val APPCOMPAT: String = "1.5.1"
            const val NAVIGATION: String = "2.5.3"
            const val LIFECYCLE: String = "2.5.1"
        }

        const val CORE_KTX: String = "androidx.core:core-ktx:${Versions.CORE_KTX}"
        const val APPCOMPAT: String = "androidx.appcompat:appcompat:${Versions.APPCOMPAT}"
        const val NAVIGATION_FRAGMENT_KTX: String = "androidx.navigation:navigation-fragment-ktx:${Versions.NAVIGATION}"
        const val NAVIGATION_UI_KTX: String = "androidx.navigation:navigation-ui-ktx:${Versions.NAVIGATION}"
        const val VIEW_MODEL_KTX: String = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.LIFECYCLE}"
        const val VIEW_MODEL_SAVED_STATE: String = "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.LIFECYCLE}"
        const val LIFECYCLE_COMPILER: String = "androidx.lifecycle:lifecycle-compiler:${Versions.LIFECYCLE}"
    }

    object Google {
        object Versions {
            const val MATERIAL: String = "1.7.0"
            const val DAGGER: String = "2.44"
            const val GSON: String = "2.10"
        }

        const val MATERIAL: String = "com.google.android.material:material:${Versions.MATERIAL}"
        const val DAGGER: String = "com.google.dagger:dagger:${Versions.DAGGER}"
        const val DAGGER_COMPILER: String = "com.google.dagger:dagger-compiler:${Versions.DAGGER}"
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

    object Test {
        object Versions {
            const val JUNIT: String = "4.13.2"
            const val JUNIT_EXT: String = "1.1.3"
            const val ESPRESSO_CORE: String = "3.4.0"
        }

        const val JUNIT: String = "junit:junit:${Versions.JUNIT}"
        const val JUNIT_EXT: String = "androidx.test.ext:junit:${Versions.JUNIT_EXT}"
        const val ESPRESSO_CORE: String = "androidx.test.espresso:espresso-core:${Versions.ESPRESSO_CORE}"
    }

}