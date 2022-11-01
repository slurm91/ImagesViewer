package by.vzhilko.imagesviewer.gradle

import by.vzhilko.imagesviewer.gradle.deps.plugin.DepsGradlePluginExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

class ExtensionsFactoryPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        createDepsGradlePluginExtension(target)
    }

    private fun createDepsGradlePluginExtension(target: Project) {
        target.extensions.create(
            DepsGradlePluginExtension.DEPS_GRADLE_PLUGIN_EXTENSION_NAME,
            DepsGradlePluginExtension::class.java
        )
    }

}