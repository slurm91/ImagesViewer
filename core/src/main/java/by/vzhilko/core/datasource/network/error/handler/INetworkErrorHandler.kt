package by.vzhilko.core.datasource.network.error.handler

interface INetworkErrorHandler {

    fun handleError(code: Int, message: String? = null): Throwable

}