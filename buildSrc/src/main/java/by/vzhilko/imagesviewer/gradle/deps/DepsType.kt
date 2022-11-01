package by.vzhilko.imagesviewer.gradle.deps

/**
 * Describes all types of dependencies in gradle's 'dependencies {}' block.
 * @see DepsType.CORE_MODULE - adds dependency of 'core' module
 * @see DepsType.ALL_FEATURE_MODULES - adds dependencies of all 'feature' modules
 * @see DepsType.DEFAULT - adds dependencies of all libraries plugged with 'implementation' extension
 * @see DepsType.TEST - adds dependencies of all libraries plugged with 'testImplementation and androidTestImplementation' extension
 */
enum class DepsType {
    CORE_MODULE,
    ALL_FEATURE_MODULES,
    DEFAULT,
    TEST
}