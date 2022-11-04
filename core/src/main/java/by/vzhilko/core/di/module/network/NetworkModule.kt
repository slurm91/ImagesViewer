package by.vzhilko.core.di.module.network

import by.vzhilko.core.datasource.network.config.DefaultHttpClientConfig
import by.vzhilko.core.datasource.network.config.IHttpClientConfig
import by.vzhilko.core.datasource.network.error.handler.DefaultNetworkErrorHandler
import by.vzhilko.core.datasource.network.error.handler.INetworkErrorHandler
import dagger.Binds
import dagger.Module

@Module(includes = [RetrofitModule::class])
interface NetworkModule {

    @Binds
    fun bindDefaultHttpClientConfig(config: DefaultHttpClientConfig): IHttpClientConfig

    @Binds
    fun bindDefaultNetworkErrorHandler(handler: DefaultNetworkErrorHandler): INetworkErrorHandler

}