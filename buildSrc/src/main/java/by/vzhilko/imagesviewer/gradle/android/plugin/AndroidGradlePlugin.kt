package by.vzhilko.imagesviewer.gradle.android.plugin

import by.vzhilko.imagesviewer.gradle.android.AndroidConstants
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension

class AndroidGradlePlugin : Plugin<Project> {

    override fun apply(target: Project) {
        configureAndroidExtension(target)
    }

    private fun configureAndroidExtension(target: Project) {
        val androidExtension: Any = target.extensions.getByName("android")
        val isLibraryExtension: Boolean = androidExtension is LibraryExtension

        (androidExtension as BaseExtension).apply {
            compileSdkVersion(AndroidConstants.COMPILE_SDK)
            configureDefaultConfigExtension(this, isLibraryExtension)
            configureBuildTypesExtension(this)
            configureCompileOptionsExtension(this)
            configureKotlinOptionsExtension(this)
            configBuildFeaturesExtension(this)
            configureComposeOptions(this)
        }
    }

    private fun configureDefaultConfigExtension(
        baseExtension: BaseExtension,
        isLibraryExtension: Boolean
    ) {
        if (isLibraryExtension) {
            baseExtension.defaultConfig {
                minSdk = AndroidConstants.DefaultConfig.MIN_SDK
                targetSdk = AndroidConstants.DefaultConfig.TARGET_SDK
                testInstrumentationRunner = AndroidConstants.DefaultConfig.TEST_INSTRUMENTATION_RUNNER
                consumerProguardFiles("consumer-rules.pro")
            }
        } else {
            baseExtension.defaultConfig {
                applicationId = AndroidConstants.DefaultConfig.APPLICATION_ID
                minSdk = AndroidConstants.DefaultConfig.MIN_SDK
                targetSdk = AndroidConstants.DefaultConfig.TARGET_SDK
                versionCode = AndroidConstants.DefaultConfig.VERSION_CODE
                versionName = AndroidConstants.DefaultConfig.VERSION_NAME
                testInstrumentationRunner = AndroidConstants.DefaultConfig.TEST_INSTRUMENTATION_RUNNER
            }
        }
    }

    private fun configureBuildTypesExtension(baseExtension: BaseExtension) {
        baseExtension.buildTypes {
            getByName("release") {
                minifyEnabled(false)
                proguardFiles(
                    baseExtension.getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            }
        }
    }

    private fun configureCompileOptionsExtension(baseExtension: BaseExtension) {
        baseExtension.compileOptions {
            sourceCompatibility = AndroidConstants.CompileOptions.JAVA_VERSION
            targetCompatibility = AndroidConstants.CompileOptions.JAVA_VERSION
        }
    }

    private fun configureKotlinOptionsExtension(baseExtension: BaseExtension) {
        (baseExtension as? ExtensionAware)?.apply {
            extensions.configure<KotlinJvmOptions>("kotlinOptions") {
                jvmTarget = AndroidConstants.KotlinOptions.JVM_TARGET
            }
        }
    }

    private fun configBuildFeaturesExtension(baseExtension: BaseExtension) {
        baseExtension.buildFeatures.apply {
            compose = true
        }
        //baseExtension.buildFeatures.viewBinding = true
        if(baseExtension is LibraryExtension) {
            baseExtension.buildFeatures.dataBinding = true
        }

        if (baseExtension is BaseAppModuleExtension) {
            baseExtension.buildFeatures.dataBinding = true
        }
    }

    private fun configureComposeOptions(baseExtension: BaseExtension) {
        baseExtension.composeOptions.apply {
            kotlinCompilerExtensionVersion = AndroidConstants.ComposeOptions.KOTLIN_COMPILER_EXTENSION_VERSION
        }
    }

}