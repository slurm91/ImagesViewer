package by.vzhilko.core.di.module.connectivity

import by.vzhilko.core.util.connectivity.IConnectivityManager
import by.vzhilko.core.util.connectivity.InternetConnectivityManager
import dagger.Binds
import dagger.Module

@Module
interface ConnectivityManagerModule {

    @Binds
    fun bindInternetConnectivityManager(manager: InternetConnectivityManager): IConnectivityManager

}