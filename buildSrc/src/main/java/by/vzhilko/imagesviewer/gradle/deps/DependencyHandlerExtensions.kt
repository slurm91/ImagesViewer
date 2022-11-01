package by.vzhilko.imagesviewer.gradle.deps

import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.addModuleImplementation(dependency: String) {
    val dependencyPair: Pair<String, String> = "path" to dependency
    add("implementation", project(mapOf(dependencyPair)))
}

fun DependencyHandler.addImplementation(dependency: String) {
    add("implementation", dependency)
}

fun DependencyHandler.addTestImplementation(dependency: String) {
    add("testImplementation", dependency)
}

fun DependencyHandler.addAndroidTestImplementation(dependency: String) {
    add("androidTestImplementation", dependency)
}

fun DependencyHandler.addKapt(dependency: String) {
    add("kapt", dependency)
}

