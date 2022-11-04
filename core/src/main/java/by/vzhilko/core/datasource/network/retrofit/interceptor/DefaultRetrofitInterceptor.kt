package by.vzhilko.core.datasource.network.retrofit.interceptor

import by.vzhilko.core.datasource.network.config.IHttpClientConfig
import okhttp3.*

class DefaultRetrofitInterceptor(
    private val config: IHttpClientConfig
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = handleRequest(chain)
        val response: Response = handleResponse(chain, request)

        return response
    }

    private fun handleRequest(chain: Interceptor.Chain): Request {
        val builder: Request.Builder = chain.request().newBuilder()
        val url: HttpUrl = builder.build().url.newBuilder().addQueryParameter("key", config.apiKey).build()
        config.headers?.forEach { (key: String, value: String) ->
            builder.addHeader(key, value)
        }

        return builder.url(url).build()
    }

    private fun handleResponse(chain: Interceptor.Chain, request: Request): Response {
        return chain.proceed(request)
    }

}
