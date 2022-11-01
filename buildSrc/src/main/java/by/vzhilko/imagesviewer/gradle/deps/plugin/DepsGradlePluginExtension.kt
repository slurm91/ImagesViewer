package by.vzhilko.imagesviewer.gradle.deps.plugin

import by.vzhilko.imagesviewer.gradle.deps.DepsType

interface DepsGradlePluginExtension {
    var depsTypeList: List<DepsType>

    companion object {
        const val DEPS_GRADLE_PLUGIN_EXTENSION_NAME: String = "depsExtension"
    }
}