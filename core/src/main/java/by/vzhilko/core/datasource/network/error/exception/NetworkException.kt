package by.vzhilko.core.datasource.network.error.exception

import by.vzhilko.core.util.BaseException

open class NetworkException(
    val code: Int,
    message: String? = null
) : BaseException(message)