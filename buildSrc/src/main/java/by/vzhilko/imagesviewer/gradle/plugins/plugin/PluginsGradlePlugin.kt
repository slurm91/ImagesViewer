package by.vzhilko.imagesviewer.gradle.plugins.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

class PluginsGradlePlugin : Plugin<Project> {

    override fun apply(target: Project) {
        configurePluginsExtension(target)
    }

    private fun configurePluginsExtension(target: Project) {
        target.apply {
            plugin("org.jetbrains.kotlin.android")
            plugin("kotlin-kapt")
            plugin("kotlin-parcelize")
            plugin("com.google.dagger.hilt.android")
        }
    }

}