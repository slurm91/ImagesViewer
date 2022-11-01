package by.vzhilko.imagesviewer.di.module

import by.vzhilko.core.ui.navigation.INavigator
import by.vzhilko.imagesviewer.Navigator
import dagger.Binds
import dagger.Module

@Module
interface NavigatorModule {

    @Binds
    fun bindNavigator(navigator: Navigator): INavigator

}