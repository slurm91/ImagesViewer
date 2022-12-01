package by.vzhilko.core.di.module.network

import android.content.Context
import by.vzhilko.core.datasource.network.config.IHttpClientConfig
import by.vzhilko.core.datasource.network.error.handler.INetworkErrorHandler
import by.vzhilko.core.datasource.network.retrofit.adapter.DefaultRetrofitCallAdapterFactory
import by.vzhilko.core.datasource.network.retrofit.interceptor.DefaultRetrofitInterceptor
import by.vzhilko.core.di.annotation.scope.AppScope
import by.vzhilko.core.util.connectivity.IConnectivityManager
import com.facebook.stetho.okhttp3.StethoInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
class RetrofitModule {

    @AppScope
    @Provides
    fun provideRetrofit(
        httpClientConfig: IHttpClientConfig,
        converterFactory: Converter.Factory,
        callAdapterFactory: CallAdapter.Factory,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(httpClientConfig.hostUrl)
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(callAdapterFactory)
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideGsonConverterFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }

    @Provides
    fun provideOkHttpClient(
        @Named("HttpLoggingInterceptor") httpLoggingInterceptor: Interceptor,
        @Named("DefaultRetrofitInterceptor") defaultRetrofitInterceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient().newBuilder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(StethoInterceptor())
            .addInterceptor(defaultRetrofitInterceptor)
            .build()
    }

    @Named("HttpLoggingInterceptor")
    @Provides
    fun provideHttpLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }
    }

    @Named("DefaultRetrofitInterceptor")
    @Provides
    fun provideDefaultRetrofitInterceptor(
        httpClientConfig: IHttpClientConfig
    ): Interceptor {
        return DefaultRetrofitInterceptor(httpClientConfig)
    }

    @Provides
    fun provideDefaultRetrofitCallAdapterFactory(
        errorHandler: INetworkErrorHandler,
        connectivityManager: IConnectivityManager,
        context: Context
    ): CallAdapter.Factory {
        return DefaultRetrofitCallAdapterFactory(errorHandler, connectivityManager, context)
    }

}