package by.vzhilko.imagesviewer.di.module

import by.vzhilko.core.ui.navigation.INavigator
import by.vzhilko.imagesviewer.Navigator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface NavigatorModule {

    @Binds
    fun bindNavigator(navigator: Navigator): INavigator

}