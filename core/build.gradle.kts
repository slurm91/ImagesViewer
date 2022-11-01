import by.vzhilko.imagesviewer.gradle.deps.DepsType
import by.vzhilko.imagesviewer.gradle.deps.plugin.*
import by.vzhilko.imagesviewer.gradle.android.plugin.*
import by.vzhilko.imagesviewer.gradle.ExtensionsFactoryPlugin
import kotlin.collections.*

//region Block 'plugins { }'
plugins {
    id("com.android.library")
    id("by.vzhilko.imagesviewer.plugins")
}
//endregion

//region Custom extensions creation
apply<ExtensionsFactoryPlugin>()
configure<DepsGradlePluginExtension> {
    depsTypeList = listOf(
        DepsType.DEFAULT,
        DepsType.TEST
    )
}
//endregion

//region Block 'android { }'
android {
    namespace = "by.vzhilko.core"
}
apply<AndroidGradlePlugin>()
//endregion

//region Block 'dependencies { }'
apply<DepsGradlePlugin>()
//endregion
