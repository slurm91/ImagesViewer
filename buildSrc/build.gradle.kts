import org.gradle.kotlin.dsl.`kotlin-dsl`

plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
    mavenCentral()
    google()
}

gradlePlugin {
    plugins {
        create("ExtensionsFactoryPlugin") {
            id = "by.vzhilko.imagesviewer.extensions"
            implementationClass = "by.vzhilko.imagesviewer.gradle.ExtensionsFactoryPlugin"
        }

        create("PluginsPlugin") {
            id = "by.vzhilko.imagesviewer.plugins"
            implementationClass = "by.vzhilko.imagesviewer.gradle.plugins.plugin.PluginsGradlePlugin"
        }

        create("AndroidPlugin") {
            id = "by.vzhilko.imagesviewer.android"
            implementationClass = "by.vzhilko.imagesviewer.gradle.android.plugin.AndroidGradlePlugin"
        }

        create("DepsPlugin") {
            id = "by.vzhilko.imagesviewer.deps"
            implementationClass = "by.vzhilko.imagesviewer.gradle.deps.plugin.DepsGradlePlugin"
        }
    }
}

dependencies {
    val gradlePluginVersion = "7.3.1"
    implementation("com.android.tools.build:gradle:${gradlePluginVersion}")

    val kotlinGradlePluginVersion = "1.7.20"
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:${kotlinGradlePluginVersion}")

    val navVersion = "2.5.3"
    implementation("androidx.navigation:navigation-safe-args-gradle-plugin:$navVersion")
}