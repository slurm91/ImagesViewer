package by.vzhilko.imagesviewer.di.component

import android.content.Context
import by.vzhilko.core.di.annotation.scope.AppScope
import by.vzhilko.core.di.module.coroutines.CoroutinesModule
import by.vzhilko.core.di.module.network.NetworkModule
import by.vzhilko.core.ui.navigation.INavigator
import by.vzhilko.details.di.component.ImageDetailsComponent
import by.vzhilko.imagesviewer.di.module.NavigatorModule
import by.vzhilko.list.di.component.ImageListComponent
import dagger.BindsInstance
import dagger.Component

@AppScope
@Component(modules = [NavigatorModule::class, CoroutinesModule::class, NetworkModule::class])
interface AppComponent : INavigator.Provider,
    ImageListComponent.Provider,
    ImageDetailsComponent.Provider {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder
        fun build(): AppComponent
    }

    override fun getNavigator(): INavigator

    override fun getImageListComponent(): ImageListComponent

    override fun getImageDetailsComponent(): ImageDetailsComponent

}