package by.vzhilko.core.datasource.network.config

interface IHttpClientConfig {

    val apiKey: String
    val hostUrl: String
    val headers: Map<String, String>?

}