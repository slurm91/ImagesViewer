package by.vzhilko.core.datasource.network.error.exception

class NoResourceFoundNetworkException(
    code: Int = 404,
    message: String? = null
) : NetworkException(code, message)