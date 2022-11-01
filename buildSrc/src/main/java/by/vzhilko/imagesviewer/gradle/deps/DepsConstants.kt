package by.vzhilko.imagesviewer.gradle.deps

object DepsConstants {

    object Module {
        const val CORE: String = ":core"
        const val FEATURE_LIST: String = ":feature:list"
        const val FEATURE_DETAILS: String = ":feature:details"
    }

    object Androidx {
        object Versions {
            const val CORE_KTX: String = "1.9.0"
            const val APPCOMPAT: String = "1.5.1"
            const val NAVIGATION: String = "2.5.3"
        }

        const val CORE_KTX: String = "androidx.core:core-ktx:${Versions.CORE_KTX}"
        const val APPCOMPAT: String = "androidx.appcompat:appcompat:${Versions.APPCOMPAT}"
        const val NAVIGATION_FRAGMENT_KTX: String = "androidx.navigation:navigation-fragment-ktx:${Versions.NAVIGATION}"
        const val NAVIGATION_UI_KTX: String = "androidx.navigation:navigation-ui-ktx:${Versions.NAVIGATION}"
    }

    object Google {
        object Versions {
            const val MATERIAL: String = "1.7.0"
            const val DAGGER: String = "2.44"
        }

        const val MATERIAL: String = "com.google.android.material:material:${Versions.MATERIAL}"
        const val DAGGER: String = "com.google.dagger:dagger:${Versions.DAGGER}"
        const val DAGGER_COMPILER = "com.google.dagger:dagger-compiler:${Versions.DAGGER}"
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