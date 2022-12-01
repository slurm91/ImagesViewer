package by.vzhilko.core.datasource.network.error.handler

import by.vzhilko.core.datasource.network.error.exception.NetworkException
import by.vzhilko.core.datasource.network.error.exception.NoResourceFoundNetworkException
import javax.inject.Inject

class DefaultNetworkErrorHandler @Inject constructor() : INetworkErrorHandler {

    override fun handleError(code: Int, message: String?): Throwable  {
        return when(code) {
            404 -> NoResourceFoundNetworkException(message = message)
            else -> NetworkException(code = code, message = message)
        }
    }

}