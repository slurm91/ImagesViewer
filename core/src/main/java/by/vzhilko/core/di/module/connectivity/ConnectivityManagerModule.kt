package by.vzhilko.core.di.module.connectivity

import by.vzhilko.core.util.connectivity.IConnectivityManager
import by.vzhilko.core.util.connectivity.InternetConnectivityManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface ConnectivityManagerModule {

    @Binds
    fun bindInternetConnectivityManager(manager: InternetConnectivityManager): IConnectivityManager

}