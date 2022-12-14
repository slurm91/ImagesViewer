import by.vzhilko.imagesviewer.gradle.ExtensionsFactoryPlugin
import by.vzhilko.imagesviewer.gradle.deps.DepsType
import by.vzhilko.imagesviewer.gradle.deps.plugin.*
import by.vzhilko.imagesviewer.gradle.android.plugin.*
import kotlin.collections.*

//region Block 'plugins { }'
plugins {
    id("com.android.application")
    id("by.vzhilko.imagesviewer.plugins")
}
//endregion

//region Custom extensions creation
apply<ExtensionsFactoryPlugin>()
configure<DepsGradlePluginExtension> {
    depsTypeList = listOf(
        DepsType.CORE_MODULE,
        DepsType.ALL_FEATURE_MODULES,
        DepsType.DEFAULT,
        DepsType.TEST
    )
}
//endregion

//region Block 'android { }'
android {
    namespace = "by.vzhilko.imagesviewer"
}
apply<AndroidGradlePlugin>()
//endregion

//region Block 'dependencies { }'
apply<DepsGradlePlugin>()
//endregion