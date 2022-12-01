package by.vzhilko.core.datasource.network.retrofit.adapter

import android.content.Context
import by.vzhilko.core.R
import by.vzhilko.core.datasource.network.NetworkState
import by.vzhilko.core.datasource.network.error.handler.INetworkErrorHandler
import by.vzhilko.core.util.connectivity.IConnectivityManager
import by.vzhilko.core.util.connectivity.exception.InternetConnectionException
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DefaultRetrofitCall<T : Any>(
    private val delegateCall: Call<T>,
    private val errorHandler: INetworkErrorHandler,
    private val connectivityManager: IConnectivityManager,
    private val context: Context
) : Call<NetworkState<T>> {

    override fun enqueue(callback: Callback<NetworkState<T>>) {
        delegateCall.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful) {
                    callback.onResponse(
                        this@DefaultRetrofitCall,
                        Response.success(NetworkState.Success(response.body()!!))
                    )
                } else {
                    val bodyAsString: String? = response.errorBody()?.string()
                    callback.onResponse(
                        this@DefaultRetrofitCall,
                        Response.success(
                            NetworkState.Error(
                                errorHandler.handleError(response.code(), bodyAsString)
                            )
                        )
                    )
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                val error: Throwable = if (!connectivityManager.isConnected()) {
                    InternetConnectionException(context.getString(R.string.no_internet_connection_message))
                } else {
                    errorHandler.handleError(-1, t.message)
                }
                callback.onResponse(
                    this@DefaultRetrofitCall,
                    Response.success(
                        NetworkState.Error(error)
                    )
                )
            }
        })
    }

    override fun clone(): Call<NetworkState<T>> {
        return DefaultRetrofitCall(delegateCall.clone(), errorHandler, connectivityManager, context)
    }

    override fun execute(): Response<NetworkState<T>> {
        return Response.success(NetworkState.Success(delegateCall.execute().body()!!))
    }

    override fun isExecuted(): Boolean {
        return delegateCall.isExecuted
    }

    override fun cancel() {
        delegateCall.cancel()
    }

    override fun isCanceled(): Boolean {
        return delegateCall.isCanceled
    }

    override fun request(): Request {
        return delegateCall.request()
    }

    override fun timeout(): Timeout {
        return delegateCall.timeout()
    }

}