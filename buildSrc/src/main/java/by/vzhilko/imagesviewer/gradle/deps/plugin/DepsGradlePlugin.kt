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
        addAndroidxDependencies(target)
        addGoogleDependencies(target)
    }

    private fun addAndroidxDependencies(target: Project) {
        target.dependencies.apply {
            addImplementation(DepsConstants.Androidx.CORE_KTX)
            addImplementation(DepsConstants.Androidx.APPCOMPAT)
            addImplementation(DepsConstants.Androidx.NAVIGATION_FRAGMENT_KTX)
            addImplementation(DepsConstants.Androidx.NAVIGATION_UI_KTX)
        }
    }

    private fun addGoogleDependencies(target: Project) {
        target.dependencies.apply {
            addImplementation(DepsConstants.Google.MATERIAL)
            addImplementation(DepsConstants.Google.DAGGER)
            addKapt(DepsConstants.Google.DAGGER_COMPILER)
        }
    }
    //endregion

    //region Test Dependencies
    private fun addTestDependencies(target: Project) {
        target.dependencies.apply {
            addTestImplementation(DepsConstants.Test.JUNIT)
            addAndroidTestImplementation(DepsConstants.Test.JUNIT_EXT)
            addAndroidTestImplementation(DepsConstants.Test.ESPRESSO_CORE)
        }
    }
    //endregion

}