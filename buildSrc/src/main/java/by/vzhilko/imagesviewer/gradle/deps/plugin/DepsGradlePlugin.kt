package by.vzhilko.imagesviewer.gradle.deps.plugin

import by.vzhilko.imagesviewer.gradle.deps.*
import org.gradle.api.Plugin
import org.gradle.api.Project

class DepsGradlePlugin : Plugin<Project> {

    override fun apply(target: Project) {
        val depsExtension: DepsGradlePluginExtension = target.extensions.getByType(DepsGradlePluginExtension::class.java)
        addDependencies(target, depsExtension)
    }

    private fun addDependencies(target: Project, depsExtension: DepsGradlePluginExtension) {
        depsExtension.depsTypeList.forEach { depsType: DepsType ->
            when (depsType) {
                DepsType.CORE_MODULE -> addCoreModuleDependency(target)
                DepsType.ALL_FEATURE_MODULES -> addFeatureModuleDependencies(target)
                DepsType.DEFAULT -> addDefaultDependencies(target)
                DepsType.TEST -> addTestDependencies(target)
            }
        }
    }

    //region Core Module Dependency
    private fun addCoreModuleDependency(target: Project) {
        target.dependencies.apply {
            addModuleImplementation(DepsConstants.Module.CORE)
        }
    }
    //endregion

    //region Feature Module Dependencies
    private fun addFeatureModuleDependencies(target: Project) {
        target.dependencies.apply {
            addModuleImplementation(DepsConstants.Module.FEATURE_LIST)
            addModuleImplementation(DepsConstants.Module.FEATURE_DETAILS)
        }
    }
    //endregion

    //region Default Dependencies
    private fun addDefaultDependencies(target: Project) {
        addKotlinDependencies(target)
        addAndroidxDependencies(target)
        addGoogleDependencies(target)
        addSquareupDependencies(target)
        addCoilDependencies(target)
    }

    private fun addKotlinDependencies(target: Project) {
        target.dependencies.apply {
            addImplementation(DepsConstants.Kotlin.COROUTINES)
        }
    }

    private fun addAndroidxDependencies(target: Project) {
        target.dependencies.apply {
            addImplementation(DepsConstants.Androidx.CORE_KTX)
            addImplementation(DepsConstants.Androidx.APPCOMPAT)
            addImplementation(DepsConstants.Androidx.NAVIGATION_FRAGMENT_KTX)
            addImplementation(DepsConstants.Androidx.NAVIGATION_UI_KTX)
            addImplementation(DepsConstants.Androidx.VIEW_MODEL_KTX)
            addImplementation(DepsConstants.Androidx.VIEW_MODEL_SAVED_STATE)
            addKapt(DepsConstants.Androidx.LIFECYCLE_COMPILER)
            addImplementation(DepsConstants.Androidx.PAGING)
            addImplementation(DepsConstants.Androidx.ROOM)
            addImplementation(DepsConstants.Androidx.ROOM_KTX)
            addImplementation(DepsConstants.Androidx.ROOM_PAGING)
            addKapt(DepsConstants.Androidx.ROOM_COMPILER)
            addDebugImplementation(DepsConstants.Test.FRAGMENT_TESTING)
            addDebugImplementation(DepsConstants.Test.MONITOR)
            addImplementation(DepsConstants.Androidx.COMPOSE_RUNTIME)
            addImplementation(DepsConstants.Androidx.COMPOSE_MATERIAL3)
            addImplementation(DepsConstants.Androidx.COMPOSE_UI_TOOLING_PREVIEW)
            addDebugImplementation(DepsConstants.Androidx.COMPOSE_UI_TOOLING)
            addImplementation(DepsConstants.Androidx.COMPOSE_ACTIVITY)
            addImplementation(DepsConstants.Androidx.COMPOSE_LIFECYCLE_VIEWMODEL)
            addImplementation(DepsConstants.Androidx.COMPOSE_NAVIGATION)
            addImplementation(DepsConstants.Androidx.COMPOSE_HILT_NAVIGATION)
            addImplementation(DepsConstants.Androidx.COMPOSE_PAGING)
        }
    }

    private fun addGoogleDependencies(target: Project) {
        target.dependencies.apply {
            addImplementation(DepsConstants.Google.MATERIAL)
            addImplementation(DepsConstants.Google.DAGGER)
            addKapt(DepsConstants.Google.DAGGER_COMPILER)
            addImplementation(DepsConstants.Google.HILT)
            addKapt(DepsConstants.Google.HILT_COMPILER)
            addImplementation(DepsConstants.Google.GSON)
        }
    }

    private fun addSquareupDependencies(target: Project) {
        target.dependencies.apply {
            addImplementation(DepsConstants.Squareup.RETROFIT)
            addImplementation(DepsConstants.Squareup.RETROFIT_CONVERTER_GSON)
            addImplementation(DepsConstants.Squareup.HTTP_LOGGING_INTERCEPTOR)
        }
    }

    private fun addCoilDependencies(target: Project) {
        target.dependencies.apply {
            addImplementation(DepsConstants.Coil.COIL)
            addImplementation(DepsConstants.Coil.COIL_COMPOSE)
        }
    }

    private fun addFacebookDependencies(target: Project) {
        target.dependencies.apply {
            addImplementation(DepsConstants.Facebook.STETHO)
            addImplementation(DepsConstants.Facebook.STETHO_OKHTTP)
        }
    }
    //endregion

    //region Test Dependencies
    private fun addTestDependencies(target: Project) {
        target.dependencies.apply {
            addTestImplementation(DepsConstants.Test.JUNIT)
            addTestImplementation(DepsConstants.Test.MOCKITO_KOTLIN)
            addTestImplementation(DepsConstants.Test.COROUTINES_TEST)
            addAndroidTestImplementation(DepsConstants.Test.MOCKITO_KOTLIN)
            addAndroidTestImplementation(DepsConstants.Test.MOCKITO_ANDROID)
            addAndroidTestImplementation(DepsConstants.Test.COROUTINES_TEST)
            addAndroidTestImplementation(DepsConstants.Test.JUNIT_EXT)
            addAndroidTestImplementation(DepsConstants.Test.ESPRESSO_CORE)
        }
    }
    //endregion

}