package by.vzhilko.core.datasource.network.retrofit.adapter

import by.vzhilko.core.datasource.network.NetworkState
import by.vzhilko.core.datasource.network.error.handler.INetworkErrorHandler
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class DefaultRetrofitCallAdapterFactory(
    private val errorHandler: INetworkErrorHandler
) : CallAdapter.Factory() {

    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if (getRawType(returnType) != Call::class.java || returnType !is ParameterizedType) {
            return null
        }

        val upperBound = getParameterUpperBound(0, returnType)
        return if (upperBound is ParameterizedType && upperBound.rawType == NetworkState::class.java) {
            object : CallAdapter<Any, Call<NetworkState<*>>> {
                override fun adapt(call: Call<Any>): Call<NetworkState<*>> {
                    return DefaultRetrofitCall(call, errorHandler)
                }

                override fun responseType(): Type {
                    return getParameterUpperBound(0, upperBound)
                }
            }
        } else {
            null
        }
    }

}