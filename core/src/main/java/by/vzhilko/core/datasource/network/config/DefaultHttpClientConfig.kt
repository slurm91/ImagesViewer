package by.vzhilko.core.datasource.network.config

import by.vzhilko.core.di.annotation.scope.AppScope
import javax.inject.Inject

@AppScope
class DefaultHttpClientConfig @Inject constructor() : IHttpClientConfig {

    override val apiKey: String get() = "31038638-96c07492393e34978fcacec93"
    override val hostUrl: String get() = "https://pixabay.com/api/"
    override val headers: Map<String, String>? get() = null

}