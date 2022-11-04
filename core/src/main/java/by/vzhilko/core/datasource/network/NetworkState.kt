package by.vzhilko.core.datasource.network

sealed class NetworkState<out T : Any> {
    class Success<out T : Any>(val response: T) : NetworkState<T>()
    class Error(val error: Throwable) : NetworkState<Nothing>()
}