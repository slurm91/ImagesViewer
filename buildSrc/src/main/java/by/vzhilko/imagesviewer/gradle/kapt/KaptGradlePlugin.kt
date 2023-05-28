package by.vzhilko.imagesviewer.gradle.kapt

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.plugin.KaptExtension

class KaptGradlePlugin : Plugin<Project> {

    override fun apply(target: Project) {
        configureKaptExtension(target)
    }

    private fun configureKaptExtension(target: Project) {
        val kaptExtension: KaptExtension? = target.extensions.getByName("kapt") as? KaptExtension
        kaptExtension?.apply {
            correctErrorTypes = true
            //TODO::remove after hilt implementation
            arguments {
                arg("dagger.hilt.disableModulesHaveInstallInCheck", "true")
            }
        }
    }

}