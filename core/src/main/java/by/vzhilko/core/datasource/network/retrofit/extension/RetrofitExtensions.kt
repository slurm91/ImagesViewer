package by.vzhilko.core.datasource.network.retrofit.extension

import retrofit2.Retrofit

inline fun <reified T> Retrofit.createApi(): T {
    return create(T::class.java)
}