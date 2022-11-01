package by.vzhilko.imagesviewer.gradle.android

import org.gradle.api.JavaVersion

object AndroidConstants {

    const val COMPILE_SDK: Int = 33

    object DefaultConfig {
        const val APPLICATION_ID: String = "by.vzhilko.imagesviewer"
        const val MIN_SDK: Int = 23
        const val TARGET_SDK: Int = 33
        const val VERSION_CODE: Int = 1
        const val VERSION_NAME: String = "1.0"
        const val TEST_INSTRUMENTATION_RUNNER: String = "androidx.test.runner.AndroidJUnitRunner"
    }

    object CompileOptions {
        val JAVA_VERSION: JavaVersion = JavaVersion.VERSION_11
    }

    object KotlinOptions {
        const val JVM_TARGET: String = "11"
    }

}